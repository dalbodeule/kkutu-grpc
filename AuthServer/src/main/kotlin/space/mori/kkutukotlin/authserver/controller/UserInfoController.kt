package space.mori.kkutukotlin.authserver.controller

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.context.request.ServletRequestAttributes
import org.springframework.web.util.WebUtils
import space.mori.kkutukotlin.authserver.database.findUser
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.HttpSession

@RestController
class UserInfoController {
    @Value("\${jwt.secret}")
    private val jwtSecret: String = ""// JWT 시크릿 키

    @GetMapping("/userinfo")
    fun getUserInfo(request: HttpServletRequest): ResponseEntity<Map<String, Any>> {
        // JWT 토큰 가져오기
        val token = getTokenFromCookie(request)

        // JWT 디코딩하여 유저 정보 추출
        val claims = Jwts.parser()
            .setSigningKey(Keys.hmacShaKeyFor(jwtSecret.toByteArray()))
            .build()
            .parseClaimsJws(token)
            .body

        // 유저 정보로부터 검색하여 반환
        val userId = claims["userId"] as String
        val vendor = claims["vendor"] as String
        val user = findUser(userId, vendor)

        // 유저 정보 반환
        val userInfo = mapOf(
            "id" to (user?.id.toString() ?: ""),
            "vendor" to (user?.vendor ?: ""),
            "profileImageUrl" to (user?.profileImage ?: ""),
            "nickname" to (user?.nickname ?: "")
        )

        return ResponseEntity.ok(userInfo)
    }

    private fun getTokenFromCookie(request: HttpServletRequest): String {
        val cookie = WebUtils.getCookie(request, "JWT_TOKEN")
        return cookie?.value ?: throw IllegalStateException("JWT_TOKEN cookie not found")
    }
}