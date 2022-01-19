package pl.polsl.barbell.model

class Set(
        var reps: Int? = null,
        var load: Int? = null
) {
    data class Builder(
        private var reps: Int? = null,
        private var load: Int? = null
    ) {
        fun reps(reps: Int?) = apply { this.reps = reps }
        fun load(load: Int?) = apply { this.load = load }
        fun build() = Set(
            reps,
            load
        )
    }
}