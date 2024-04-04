package space.mori.kkutukotlin.authserver

data class OAuth2Attribute(
    val email: String?,
    val nickname: String?,
    val picture: String? = null,
    val provider: String?,
    val uid: String?
) {
    companion object {
        // ���񽺿� ���� OAuth2Attribute ��ü�� �����ϴ� �޼���
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
    *   Google �α����� ��� ����ϴ� �޼���, ����� ������ ���� Wrapping ���� �ʰ� �����Ǿ�,
    *   �ٷ� get() �޼���� ������ �����ϴ�.
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
