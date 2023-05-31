package it.polito.wa2.g11.ticketing.authorization

import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import org.keycloak.admin.client.Keycloak
import org.keycloak.admin.client.KeycloakBuilder
import org.keycloak.representations.idm.CredentialRepresentation
import org.keycloak.representations.idm.RoleRepresentation
import org.keycloak.representations.idm.UserRepresentation
import org.springframework.stereotype.Service
import javax.ws.rs.core.Response

private const val KEYCLOAK_SERVER_URL = "http://keycloak:8080/"
private const val KEYCLOAK_MASTER_REALM = "master"
private const val KEYCLOAK_REALM = "SpringBootKeycloak"
private const val KEYCLOAK_MASTER_CLIENT_ID = "admin-cli"
private const val KEYCLOAK_USERNAME = "admin"
private const val KEYCLOAK_PASSWORD = "admin"

@Service
class AuthorizationServiceImpl : AuthorizationService {

    private val client = OkHttpClient()
    private val baseUrl = "http://keycloak:8080/realms/SpringBootKeycloak/protocol/openid-connect/token"
    private var accessToken: String? = null
    private val keycloak: Keycloak = KeycloakBuilder.builder()
        .serverUrl(KEYCLOAK_SERVER_URL)
        .realm(KEYCLOAK_MASTER_REALM)
        .clientId(KEYCLOAK_MASTER_CLIENT_ID)
        .username(KEYCLOAK_USERNAME)
        .password(KEYCLOAK_PASSWORD)
        .build()

    override fun login(username: String, password: String): Map<String, String?> {
        val token = getToken(username, password)
        if (token != null) {
            accessToken = token
            return mapOf("token" to token)
        } else {
            throw Exception("Login failed")
        }
    }

    override fun logout(token: String) {
        accessToken = null
    }

    override fun signup(username: String, password: String, email: String): Response? {
        val response = addUser(username, password, email)
        assignRoleToUser(username, "app_client")
        return Response.status(response?.status ?: 500).build()
    }

    override fun createExpert(username: String, password: String, email: String): Response? {
        val response = addUser(username, password, email)
        assignRoleToUser(username, "app_expert")
        return Response.status(response?.status ?: 500).build()
    }

    private fun assignRoleToUser(username: String, role: String) {
        val userRepresentation: UserRepresentation =
            keycloak.realm(KEYCLOAK_REALM).users().search(username).firstOrNull() ?: return

        val roleRepresentation: RoleRepresentation =
            keycloak.realm(KEYCLOAK_REALM).roles().get(role).toRepresentation()

        keycloak.realm(KEYCLOAK_REALM).users().get(userRepresentation.id).roles().realmLevel()
            .add(listOf(roleRepresentation))
    }

    private fun addUser(username: String, password: String, email: String): Response? {
        val userRepresentation = UserRepresentation()
        userRepresentation.username = username
        userRepresentation.isEnabled = true
        userRepresentation.email = email
        userRepresentation.isEmailVerified = true

        val credentialRepresentation = CredentialRepresentation()
        credentialRepresentation.type = CredentialRepresentation.PASSWORD
        credentialRepresentation.isTemporary = false
        credentialRepresentation.value = password

        userRepresentation.credentials = listOf(credentialRepresentation)

        return keycloak.realm(KEYCLOAK_REALM).users().create(userRepresentation)
    }

    private fun getToken(username: String, password: String): String? {
        val formBody = FormBody.Builder()
            .add("grant_type", "password")
            .add("client_id", "springboot-keycloak-client")
            .add("username", username)
            .add("password", password)
            .build()

        val request = Request.Builder()
            .header("Content-Type", "application/x-www-form-urlencoded")
            .url(baseUrl)
            .post(formBody)
            .build()

        val response = client.newCall(request).execute()

        return if (response.isSuccessful) {
            val responseBody = response.body?.string()
            extractAccessToken(responseBody)
        } else {
            println("Request failed: ${response.code}")
            null
        }
    }

    private fun extractAccessToken(responseBody: String?): String? {
        return responseBody?.let {
            val json = JSONObject(it)
            json.getString("access_token")
        }
    }
}

