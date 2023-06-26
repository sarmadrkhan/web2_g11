package it.polito.wa2.g11.ticketing.security

import com.nimbusds.jose.shaded.gson.internal.LinkedTreeMap
import org.springframework.core.convert.converter.Converter
import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.security.oauth2.jwt.JwtClaimNames
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter
import org.springframework.stereotype.Component
import java.util.stream.Collectors
import java.util.stream.Stream


@Component
class JwtAuthConverter(private val properties: JwtAuthConverterProperties? = null) :
    Converter<Jwt, AbstractAuthenticationToken> {

    private val jwtGrantedAuthoritiesConverter: JwtGrantedAuthoritiesConverter = JwtGrantedAuthoritiesConverter()
    override fun convert(jwt: Jwt): AbstractAuthenticationToken {
        val authorities: Collection<GrantedAuthority> = Stream.concat(
            jwtGrantedAuthoritiesConverter.convert(jwt)!!.stream(),
            extractResourceRoles(jwt).stream()
        )
            .collect(Collectors.toSet())
        return JwtAuthenticationToken(jwt, authorities, getPrincipalClaimName(jwt))
    }

    private fun getPrincipalClaimName(jwt: Jwt): String {
        val claimName: String = properties?.principalAttribute ?: JwtClaimNames.SUB
        return jwt.claims[claimName] as String
    }

    private fun extractResourceRoles(jwt: Jwt): Collection<GrantedAuthority> {
        val resourceAccess: Map<String, Any> = if (jwt.claims["resource_access"] != null) {
            jwt.claims["resource_access"] as LinkedTreeMap<String, LinkedTreeMap<String, ArrayList<String>>>
        } else {
            emptyMap()
        }
        val resource: Map<String, Any> = if (properties?.resourceId != null) {
            resourceAccess[properties.resourceId] as LinkedTreeMap<String, ArrayList<String>>
        } else {
            emptyMap()
        }
        val resourceRoles: Collection<String> = if (resource["roles"] != null) {
            resource["roles"] as ArrayList<String>
        } else {
            emptyList()
        }
        if (resourceAccess.isEmpty()
            || resource.isEmpty()
            || resourceRoles.isEmpty()
        ) {
            return emptySet()
        }
        return resourceRoles.stream()
            .map { SimpleGrantedAuthority("ROLE_$it") }
            .collect(Collectors.toSet())
    }
}

