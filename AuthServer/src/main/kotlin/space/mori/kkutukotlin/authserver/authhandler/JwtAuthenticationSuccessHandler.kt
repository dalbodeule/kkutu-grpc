package space.mori.kkutukotlin.authserver.authhandler

import com.fasterxml.jackson.databind.ObjectMapper
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.MediaType
import org.springframework.security.core.Authentication
import org.springframework.security.oauth2.core.user.DefaultOAuth2User
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import javax.servlet.http.Cookie


import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtAuthenticationSuccessHandler(
    private val objectMapper: ObjectMapper, // JSON 직렬화를 위한 ObjectMapper
) : AuthenticationSuccessHandler {
    @Value("\${jwt.secret}")
    private val jwtSecret: String = ""// JWT 시크릿 키
    @Value("\${jwt.expire}")
    private val jwtExpirationTime: Long = 3600 // JWT 만료 시간

    @Value("\${FRONT_URL}")
    private val frontUrl: String = ""

    override fun onAuthenticationSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication
    ) {
        val oauth2User = authentication.principal as DefaultOAuth2User

        // Extract user attributes from OAuth2 user
        val attributes = oauth2User.attributes
        val userId = attributes["userId"] as String
        val vendor = attributes["vendor"] as String
        val profileImageUrl = attributes["profile"] as String
        val nickname = attributes["nickname"] as String

        // Generate JWT token
        val token = Jwts.builder()
            .setSubject(authentication.name)
            .claim("userId", userId)
            .claim("vendor", vendor)
            .claim("profileImageUrl", profileImageUrl)
            .claim("nickname", nickname)
            .setExpiration(Date(System.currentTimeMillis() + jwtExpirationTime * 1000))
            .signWith(Keys.hmacShaKeyFor(jwtSecret.toByteArray()))
            .compact()

        // Set JWT token in cookie
        val cookie = Cookie("JWT_TOKEN", token)
        cookie.path="/"
        cookie.isHttpOnly = true
        cookie.maxAge = jwtExpirationTime.toInt()
        response.addCookie(cookie)

        // Redirect to FRONT_URL
        response.sendRedirect(frontUrl)
    }
}