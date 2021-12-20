package pl.polsl.barbell.model

class Exercise(
        val id: String? = null,
        val name: String? = null,
        val description: String? = null,
        val pictureUrl: String? = null
) {
    data class Builder(
            private val id: String,
            private val name: String,
            private var description: String? = null,
            private var url: String? = null
    ) {
        fun description(description: String?) = apply { this.description = description }
        fun url(url: String?) = apply { this.url = url }
        fun build() = Exercise(
                id,
                name,
                description,
                url
        )
    }
}