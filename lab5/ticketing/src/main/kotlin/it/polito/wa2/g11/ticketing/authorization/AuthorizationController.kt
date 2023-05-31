package it.polito.wa2.g11.ticketing.authorization

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.ws.rs.core.Response

@RestController
class AuthorizationController(
    private val authorizationService: AuthorizationService
) {
    @PostMapping("/login")
    fun login(@RequestParam username: String, @RequestParam password: String): Map<String, String?> {
        return authorizationService.login(username, password)
    }

    @PostMapping("/logout")
    fun logout(@RequestParam token: String) {
        authorizationService.logout(token)
    }

    @PostMapping("/signup")
    fun signup(@RequestParam username: String, @RequestParam password: String, @RequestParam email: String): Response? {
        return authorizationService.signup(username, password, email)
    }

    @PostMapping("/createExpert")
    fun createExpert(@RequestParam username: String, @RequestParam password: String, @RequestParam email: String): Response? {
        return authorizationService.createExpert(username, password, email)
    }
}