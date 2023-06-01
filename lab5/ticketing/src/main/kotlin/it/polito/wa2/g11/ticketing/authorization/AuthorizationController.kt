package it.polito.wa2.g11.ticketing.authorization

import io.micrometer.observation.annotation.Observed
import lombok.extern.slf4j.Slf4j
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.ws.rs.core.Response

@RestController
@Slf4j
@Observed
class AuthorizationController(
    private val authorizationService: AuthorizationService
) {

    private val log: Logger = LoggerFactory.getLogger(AuthorizationController::class.java)

    @PostMapping("/login")
    fun login(@RequestParam username: String, @RequestParam password: String): Map<String, String?> {
        log.info("Login request for user $username")
        return authorizationService.login(username, password)
    }

    @PostMapping("/logout")
    fun logout(@RequestParam token: String) {
        log.info("Logout request for token $token")
        authorizationService.logout(token)
    }

    @PostMapping("/signup")
    fun signup(@RequestParam username: String, @RequestParam password: String, @RequestParam email: String): Response? {
        log.info("Signup request for user $username")
        return authorizationService.signup(username, password, email)
    }

    @PostMapping("/createExpert")
    fun createExpert(
        @RequestParam username: String,
        @RequestParam password: String,
        @RequestParam email: String
    ): Response? {
        log.info("Create expert request for user $username")
        return authorizationService.createExpert(username, password, email)
    }
}