package it.polito.wa2.g11.ticketing.security

import org.springframework.http.ResponseEntity
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
@RequestMapping("/test")
class TestSecurityController {

    @GetMapping("/anonymous")
    fun getAnonymous(): ResponseEntity<String> {
        return ResponseEntity.ok("Hello Anonymous")
    }

    @GetMapping("/expert")
    fun getExpert(principal: Principal): ResponseEntity<String> {
        val token = principal as JwtAuthenticationToken
        val userName = token.tokenAttributes["name"]
        val userEmail = token.tokenAttributes["email"]
        return ResponseEntity.ok("Hello Expert \nUser Name : $userName\nUser Email : $userEmail")
    }

    @GetMapping("/client")
    fun getClient(principal: Principal): ResponseEntity<String> {
        val token = principal as JwtAuthenticationToken
        val userName = token.tokenAttributes["name"]
        val userEmail = token.tokenAttributes["email"]
        return ResponseEntity.ok("Hello Client \nUser Name : $userName\nUser Email : $userEmail")
    }

    @GetMapping("/manager")
    fun getManager(principal: Principal): ResponseEntity<String> {
        val token = principal as JwtAuthenticationToken
        val userName = token.tokenAttributes["name"]
        val userEmail = token.tokenAttributes["email"]
        return ResponseEntity.ok("Hello Manager \nUser Name : $userName\nUser Email : $userEmail")
    }
}