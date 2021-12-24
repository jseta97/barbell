package pl.polsl.barbell.model

import kotlin.collections.ArrayList

class ExercisesWithSets(
        val exercise: Exercise? = null,
        val sets: ArrayList<Set>? = null
) {
    data class Builder(
            private var exercise: Exercise? = null,
            private var sets: ArrayList<Set> = arrayListOf(),
    ) {
        fun sets(sets: ArrayList<Set>) = apply { this.sets = sets }
        fun exercise(exercise: Exercise) = apply { this.exercise = exercise }
        fun build() = ExercisesWithSets(
                exercise,
                sets
        )
    }
}