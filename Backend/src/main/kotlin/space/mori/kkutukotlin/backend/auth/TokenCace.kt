package space.mori.kkutukotlin.backend.auth

import java.util.concurrent.ConcurrentHashMap

class TokenCache {
    data class CachedToken(val userInfo: String, val expirationTimeMillis: Long)

    private val cache = ConcurrentHashMap<String, CachedToken>()

    fun putToken(token: String, userInfo: String, expirationTimeMillis: Long) {
        cache[token] = CachedToken(userInfo, expirationTimeMillis)
    }

    fun getToken(token: String): String? {
        val cachedToken = cache[token]
        if (cachedToken != null && cachedToken.expirationTimeMillis > System.currentTimeMillis()) {
            return cachedToken.userInfo
        }
        // 만료된 토큰이거나 캐시에 없는 경우
        return null
    }
}