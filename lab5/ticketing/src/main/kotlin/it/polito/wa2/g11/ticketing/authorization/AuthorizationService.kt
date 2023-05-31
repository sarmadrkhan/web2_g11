package it.polito.wa2.g11.ticketing.authorization

import javax.ws.rs.core.Response

interface AuthorizationService {

    fun login(username: String, password: String): Map<String, String?>

    fun logout(token: String)

    fun signup(username: String, password: String, email: String): Response?

    fun createExpert(username: String, password: String, email: String): Response?
}