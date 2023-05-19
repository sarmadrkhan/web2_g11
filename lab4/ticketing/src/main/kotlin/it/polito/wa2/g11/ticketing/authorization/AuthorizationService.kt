package it.polito.wa2.g11.ticketing.authorization

interface AuthorizationService {

    fun login(username: String, password: String): String

    fun logout(token: String)

}