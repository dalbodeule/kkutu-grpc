package space.mori.kkutukotlin.backend.auth

import com.linecorp.armeria.client.WebClient
import com.linecorp.armeria.common.HttpRequest
import com.linecorp.armeria.common.RequestContext
import com.linecorp.armeria.server.ServiceRequestContext
import io.grpc.Context
import io.grpc.ServerCall
import io.grpc.ServerCallHandler
import io.grpc.ServerInterceptor
import io.netty.util.AttributeKey
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.*
import space.mori.kkutukotlin.backend.logger
import java.io.IOException
import java.util.concurrent.CompletableFuture
import java.util.concurrent.TimeUnit

class JwtTokenInterceptor : ServerInterceptor {
    companion object {
        private const val AUTHORIZATION_HEADER = "authorization"
        private const val USER_INFO_ENDPOINT = "http://localhost:8080"
        val USER_INFO_KEY: AttributeKey<String> = AttributeKey.newInstance("user")
        private val tokenCache = TokenCache()
    }

    private val client = OkHttpClient()

    override fun <ReqT : Any?, RespT : Any?> interceptCall(
        call: ServerCall<ReqT, RespT>?,
        headers: io.grpc.Metadata?,
        next: ServerCallHandler<ReqT, RespT>?
    ): ServerCall.Listener<ReqT> {
        val jwtToken = headers?.get(io.grpc.Metadata.Key.of(AUTHORIZATION_HEADER, io.grpc.Metadata.ASCII_STRING_MARSHALLER))
        jwtToken?.let {
            val userInfo = getUserInfoFromCache(jwtToken)
            if (userInfo == null) {
                val newUserInfo = getUserInfo(jwtToken.substring(7))
                ServiceRequestContext.current().setAttr(USER_INFO_KEY, newUserInfo)
                val expirationTimeMillis = calculateExpirationTime()
                tokenCache.putToken(jwtToken, newUserInfo, expirationTimeMillis)
            } else {
                ServiceRequestContext.current().setAttr(USER_INFO_KEY, userInfo)
            }
        }
        return next!!.startCall(call, headers)
    }

    private fun getUserInfo(jwtToken: String): String {
        val completableFuture = CompletableFuture<String>()

        val request = Request.Builder()
            .url("${USER_INFO_ENDPOINT}/userinfo")
            .header("Authorization", "Bearer $jwtToken")
            .build()

        OkHttpClient().newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                completableFuture.completeExceptionally(e)
            }

            override fun onResponse(call: Call, response: Response) {
                if (!response.isSuccessful) {
                    completableFuture.completeExceptionally(IOException("Failed to get user info: ${response.code}"))
                }

                val body = response.body?.string()
                completableFuture.complete(body ?: "")
            }
        })

        return completableFuture.get()
    }

    private fun getUserInfoFromCache(jwtToken: String): String? {
        return tokenCache.getToken(jwtToken)
    }

    private fun calculateExpirationTime(): Long {
        // 만료 시간을 현재 시간으로부터 1시간으로 설정한 예시
        return System.currentTimeMillis() + TimeUnit.HOURS.toMillis(1)
    }
}