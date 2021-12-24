package pl.polsl.barbell.model

import java.util.*
import kotlin.collections.ArrayList

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
}