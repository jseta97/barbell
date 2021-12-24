package pl.polsl.barbell.model

class Exercise(
        val uuid: String? = null,
        val name: String? = null,
        val description: String? = null,
        val url: String? = null
) {
    data class Builder(
            private val uuid: String,
            private val name: String,
            private var description: String? = null,
            private var url: String? = null
    ) {
        fun description(description: String?) = apply { this.description = description }
        fun url(url: String?) = apply { this.url = url }
        fun build() = Exercise(
                uuid,
                name,
                description,
                url
        )
    }
}