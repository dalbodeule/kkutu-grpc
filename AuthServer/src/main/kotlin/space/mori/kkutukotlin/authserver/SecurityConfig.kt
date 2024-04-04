package space.mori.kkutukotlin.authserver

import com.fasterxml.jackson.databind.ObjectMapper
import com.nimbusds.oauth2.sdk.auth.JWTAuthentication
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import space.mori.kkutukotlin.authserver.authhandler.JwtAuthenticationSuccessHandler


@Configuration
@EnableWebSecurity
open class SecurityConfig {
    @Value("\${FRONT_URL}")
    private var frontUrl: String = ""

    @Bean
    @Throws(Exception::class)
    open fun filterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .httpBasic().disable()
            .sessionManagement().disable()
            .cors().and()
            .csrf().disable()
            .sessionManagement()
            .and()
            .authorizeRequests {
                it.antMatchers("/**").permitAll()
            }.oauth2Login()
            .userInfoEndpoint().userService(CustomOAuth2UserService())
            .and()
            .successHandler(jwtAuthSuccessHandler())
            .failureUrl(frontUrl)
            .and()
            .logout { it.logoutSuccessUrl(frontUrl) }
        .build()
    }

    @Bean
    open fun jwtAuthSuccessHandler(): JwtAuthenticationSuccessHandler {
        return JwtAuthenticationSuccessHandler(ObjectMapper())
    }
}