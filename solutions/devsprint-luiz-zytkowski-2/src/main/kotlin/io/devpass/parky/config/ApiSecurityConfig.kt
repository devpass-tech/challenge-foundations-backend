package io.devpass.parky.config

import io.devpass.parky.config.filters.APIKeyAuthFilter
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain


@Configuration
@EnableWebSecurity
class APISecurityConfig {

    @Value("\${service.secret}")
    private lateinit var serviceSecret: String

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        val apiKeyAuthFilter = APIKeyAuthFilter().also {
            it.setAuthenticationManager { authentication ->
                val providedKey = authentication.principal as String
                if (serviceSecret != providedKey)
                    throw BadCredentialsException("The API key was not found or not the expected value.")
                authentication.isAuthenticated = true
                authentication
            }
        }
        http
            .antMatcher("**")
            .csrf()
            .disable()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .addFilter(apiKeyAuthFilter)
            .authorizeRequests()
            .anyRequest()
            .authenticated()
        return http.build()
    }
}