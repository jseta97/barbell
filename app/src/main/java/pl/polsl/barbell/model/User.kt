package pl.polsl.barbell.model

class User(
        val uuid: String? = null,
        val name: String? = null,
        val email: String? = null,
        val url: String? = null
) {
    data class Builder(
            private val uuid: String,
            private val name: String,
            private var email: String? = null,
            private var url: String? = null
    ) {
        fun email(email: String?) = apply { this.email = email }
        fun url(url: String?) = apply { this.url = url }
        fun build() = User(
                uuid,
                name,
                email,
                url
        )
    }
}