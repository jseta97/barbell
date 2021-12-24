package pl.polsl.barbell.model

class Set(
        val reps: Int? = null
) {
    data class Builder(
            private var reps: Int? = null
    ) {
        fun reps(reps: Int?) = apply { this.reps = reps }
        fun build() = Set(
                reps
        )
    }
}