package pl.polsl.barbell.model

import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.Set

class Workout(
        val uuid: String? = null,
        val userUuid: String? = null,
        val note: String? = null,
        val exercises: ArrayList<ExercisesWithSets>? = null,
        val date: Date? = null
) {
    data class Builder(
            private val uuid: String,
            private val userUuid: String,
            private val note: String,
            private var exercises: ArrayList<ExercisesWithSets> = arrayListOf(),
            private var date: Date? = null
    ) {
        fun date(date: Date?) = apply { this.date = date }
        fun exercises(exercises: List<ExercisesWithSets>) = apply { this.exercises.addAll(exercises) }
        fun build() = Workout(
                uuid,
                userUuid,
                note,
                exercises,
                date
        )
    }

    fun getTotalVolume(): Int {
        var volume: Int = 0;
        for (exercise: ExercisesWithSets in exercises!!) {
            for (set: pl.polsl.barbell.model.Set in exercise.sets!!) {
                if (set.load == null) {
                    set.load = 0
                }
                if (set.reps == null) {
                    set.reps = 0
                }
                volume += set.load!! * set.reps!!
            }
        }
        return volume;
    }
}