package pl.polsl.barbell.model

class Set(
        var reps: Int? = null,
        var load: Int? = null,
        var setCounter: Int? = null
) {
    data class Builder(
        private var reps: Int? = null,
        private var load: Int? = null,
        private var setCounter: Int? = null
    ) {
        fun reps(reps: Int?) = apply { this.reps = reps }
        fun load(load: Int?) = apply { this.load = load }
        fun setCounter(setCounter: Int?) = apply { this.setCounter = setCounter }
        fun build() = Set(
            reps,
            load,
            setCounter
        )
    }
}