package pl.polsl.barbell.model

/**
 * Exercise
 *
 * @property uuid
 * @property name
 * @property description
 * @property bodyPart
 * @property url
 * @constructor Create empty Exercise
 */
class Exercise(
        val uuid: String? = null,
        val name: String? = null,
        val description: String? = null,
        val bodyPart: String? = null,
        val url: String? = null
) {
    /**
     * Builder
     *
     * @property uuid
     * @property name
     * @property description
     * @property bodyPart
     * @property url
     * @constructor Create empty Builder
     */
    data class Builder(
            private val uuid: String,
            private val name: String,
            private var description: String? = null,
            private var bodyPart: String? = null,
            private var url: String? = null
    ) {
        /**
         * Description
         *
         * @param description
         */
        fun description(description: String?) = apply { this.description = description }

        /**
         * Body part
         *
         * @param bodyPart
         */
        fun bodyPart(bodyPart: String?) = apply { this.bodyPart = bodyPart }

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
        fun build() = Exercise(
                uuid,
                name,
                description,
                bodyPart,
                url
        )
    }
}