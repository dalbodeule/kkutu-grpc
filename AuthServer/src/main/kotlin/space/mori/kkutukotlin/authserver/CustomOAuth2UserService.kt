package space.mori.kkutukotlin.authserver

import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService
import org.springframework.security.oauth2.core.user.DefaultOAuth2User
import org.springframework.security.oauth2.core.user.OAuth2User
import space.mori.kkutukotlin.authserver.database.createUser
import space.mori.kkutukotlin.authserver.database.findUser
import java.util.*

class CustomOAuth2UserService: OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    override fun loadUser(userRequest: OAuth2UserRequest): OAuth2User {
        val delegate = DefaultOAuth2UserService()
        val oauth2User = delegate.loadUser(userRequest)

        try {
            // Extract user's unique ID from OAuth2 provider's response
            val userId = getUserId(oauth2User, userRequest.clientRegistration.registrationId)

            // Extract user's vendor from OAuth2 provider's response
            val vendor = userRequest.clientRegistration.registrationId

            // Extract user's profile image URL from OAuth2 provider's response
            val profileImageUrl = getProfileImageUrl(oauth2User, userRequest.clientRegistration.registrationId)
            val nickname = getNickname(oauth2User, userRequest.clientRegistration.registrationId)

            // Add additional attributes to OAuth2 user
            val attributes = oauth2User.attributes.toMutableMap()
            attributes["userId"] = userId
            attributes["vendor"] = vendor
            attributes["profile"] = profileImageUrl
            attributes["nickname"] = nickname

            val oAuth2UserInfo = OAuth2Attribute.of(vendor, attributes)
            return signInOrUpdateUser(userId, vendor, nickname, profileImageUrl, attributes)
        } catch (ex: Exception) {
            throw ex
        }
    }

    private fun getUserId(oauth2User: OAuth2User, registrationId: String): String {
        // Implement logic to extract user's unique ID based on registrationId
        // This may vary depending on the OAuth2 provider (Google, Facebook, etc.)
        return when (registrationId) {
            "google" -> oauth2User.getAttribute<String?>("sub")?.toString() ?: ""
            else -> ""
        }
    }

    private fun getProfileImageUrl(oauth2User: OAuth2User, registrationId: String): String {
        // Implement logic to extract user's profile image URL based on registrationId
        // This may vary depending on the OAuth2 provider (Google, Facebook, etc.)
        return when (registrationId) {
            "google" -> oauth2User.getAttribute("picture") ?: ""
            // Add cases for other providers as needed
            else -> ""
        }
    }

    private fun getNickname(oauth2User: OAuth2User, registrationId: String): String {
        // 구글의 경우 닉네임은 "name" 속성에 있을 수 있습니다.
        return when(registrationId) {
            "google" -> oauth2User.getAttribute("name") ?: ""
            else -> oauth2User.getAttribute("name") ?: ""
        }
    }

    private fun signInOrUpdateUser(
        userId: String,
        vendor: String,
        nickname: String,
        profileImage: String,
        attributes: MutableMap<String, Any>
    ): DefaultOAuth2User {
        val user = findUser(userId, vendor)

        return if(user != null) {
            transaction {
                user.profileImage = profileImage
            }
            DefaultOAuth2User(
                Collections.singleton(SimpleGrantedAuthority("ROLE_USER")),
                attributes,
                "nickname"
            )
        } else {
            createUser(userId, vendor, nickname, profileImage)
            DefaultOAuth2User(
                Collections.singleton(SimpleGrantedAuthority("ROLE_USER")),
                attributes,
                "nickname"
            )
        }
    }
}