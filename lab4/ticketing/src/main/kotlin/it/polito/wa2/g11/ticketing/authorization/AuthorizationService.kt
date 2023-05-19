package it.polito.wa2.g11.ticketing.authorization

interface AuthorizationService {

    fun login(username: String, password: String): Map<String, String?>

    fun logout(token: String)

}