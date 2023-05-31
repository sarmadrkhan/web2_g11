package it.polito.wa2.g11.ticketing.security

import lombok.RequiredArgsConstructor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
class WebSecurityConfig(private val jwtAuthConverter: JwtAuthConverter) {
    @Bean
    @Throws(Exception::class)
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.authorizeHttpRequests()
            .requestMatchers(HttpMethod.GET, "/test/anonymous").permitAll()
            .requestMatchers(HttpMethod.GET, "/test/expert").hasRole(EXPERT)
            .requestMatchers(HttpMethod.GET, "/test/client").hasRole(CLIENT)
            .requestMatchers(HttpMethod.GET, "/test/manager").hasRole(MANAGER)
            .requestMatchers(HttpMethod.POST, "/login").permitAll()
            .requestMatchers(HttpMethod.POST, "/logout").permitAll()
            .requestMatchers(HttpMethod.POST, "/signup").permitAll()
            .requestMatchers(HttpMethod.POST, "/createExpert").hasRole(MANAGER)
            .requestMatchers(HttpMethod.GET, "/tickets", "/ticket/**").hasAnyRole(EXPERT, MANAGER, CLIENT)
            .requestMatchers(HttpMethod.POST, "/ticket").hasRole(CLIENT)
            .requestMatchers(HttpMethod.PUT, "/ticket/**").hasAnyRole(CLIENT, MANAGER)
            .requestMatchers(HttpMethod.DELETE, "/ticket/**").hasAnyRole(CLIENT, MANAGER)
            .requestMatchers(HttpMethod.GET, "/messages", "/message/**").hasAnyRole(EXPERT, MANAGER, CLIENT)
            .requestMatchers(HttpMethod.POST, "/message").hasAnyRole(EXPERT, CLIENT)
            .requestMatchers(HttpMethod.PUT, "/message/**").hasAnyRole(EXPERT, CLIENT, MANAGER)
            .requestMatchers(HttpMethod.DELETE, "/message/**").hasAnyRole(EXPERT, CLIENT, MANAGER)
            .and().csrf().disable()
        http.oauth2ResourceServer()
            .jwt()
            .jwtAuthenticationConverter(jwtAuthConverter)
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        return http.build()
    }

    companion object {
        const val EXPERT = "expert"
        const val CLIENT = "client"
        const val MANAGER = "manager"
    }
}