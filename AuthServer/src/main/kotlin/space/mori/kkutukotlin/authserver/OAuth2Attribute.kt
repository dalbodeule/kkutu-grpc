package space.mori.kkutukotlin.authserver

data class OAuth2Attribute(
    val email: String?,
    val nickname: String?,
    val picture: String? = null,
    val provider: String?,
    val uid: String?
) {
    companion object {
        // 서비스에 따라 OAuth2Attribute 객체를 생성하는 메서드
        fun of(
            provider: String?,
            attributes: Map<String, Any>
        ): OAuth2Attribute {
            return when (provider) {
                "google" -> ofGoogle(provider, attributes)
                else -> throw RuntimeException()
            }
        }

        /*
    *   Google 로그인일 경우 사용하는 메서드, 사용자 정보가 따로 Wrapping 되지 않고 제공되어,
    *   바로 get() 메서드로 접근이 가능하다.
    * */
        private fun ofGoogle(
            provider: String,
            attributes: Map<String, Any>
        ): OAuth2Attribute {
            return OAuth2Attribute(
                email = attributes["email"] as String?,
                provider = provider,
                picture = attributes["profile"] as String?,
                nickname = attributes["nickname"] as String?,
                uid = attributes["userId"] as String?
            )
        }
    }
}
