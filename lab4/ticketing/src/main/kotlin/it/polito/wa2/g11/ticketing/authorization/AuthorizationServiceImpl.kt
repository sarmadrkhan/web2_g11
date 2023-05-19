package it.polito.wa2.g11.ticketing.authorization

import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import org.springframework.stereotype.Service

@Service
class AuthorizationServiceImpl : AuthorizationService {

    private val client = OkHttpClient()
    private val baseUrl = "http://keycloak:8080/realms/SpringBootKeycloak/protocol/openid-connect/token"
    private var accessToken: String? = null
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

    fun getToken(username: String, password: String): String? {
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

    fun extractAccessToken(responseBody: String?): String? {
        return responseBody?.let {
            val json = JSONObject(it)
            json.getString("access_token")
        }
    }
}

