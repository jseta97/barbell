package pl.polsl.barbell.model

/**
 * User
 *
 * @property uuid
 * @property name
 * @property email
 * @property url
 * @constructor Create empty User
 */
class User(
        val uuid: String? = null,
        val name: String? = null,
        val email: String? = null,
        val url: String? = null
) {
    /**
     * Builder
     *
     * @property uuid
     * @property name
     * @property email
     * @property url
     * @constructor Create empty Builder
     */
    data class Builder(
            private val uuid: String,
            private val name: String,
            private var email: String? = null,
            private var url: String? = null
    ) {
        /**
         * Email
         *
         * @param email
         */
        fun email(email: String?) = apply { this.email = email }

        /**
         * Url
         *
         * @param url
         */
        fun url(url: String?) = apply { this.url = url }

        /**
         * Build
         *
         */
        fun build() = User(
                uuid,
                name,
                email,
                url
        )
    }
}