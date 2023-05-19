package it.polito.wa2.g11.ticketing.authorization

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthorizationController(
    private val authorizationService: AuthorizationService
) {
    @PostMapping("/login")
    @PreAuthorize("permitAll()")
    fun login(@RequestParam username: String, @RequestParam password: String): String {
        return authorizationService.login(username, password)
    }

    @PostMapping("/logout")
    @PreAuthorize("isAuthenticated()")
    fun logout(@RequestParam token: String) {
        authorizationService.logout(token)
    }
}