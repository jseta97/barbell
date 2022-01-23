package pl.polsl.barbell.model

import com.google.firebase.firestore.DocumentId
import java.util.*
import kotlin.collections.ArrayList

class Workout(
        @DocumentId
        val uuid: String? = null,
        val userUuid: String? = null,
        val note: String? = null,
        val exercises: ArrayList<ExercisesWithSets>? = null,
        val date: Date? = null
) {
    data class Builder(
            private var userUuid: String?,
            private val note: String,
            private var exercises: ArrayList<ExercisesWithSets> = arrayListOf(),
            private var date: Date? = null
    ) {
        fun userUuid(userUuid: String?) = apply { this.userUuid = userUuid }
        fun date(date: Date?) = apply { this.date = date }
        fun exercises(exercises: List<ExercisesWithSets>) = apply { this.exercises.addAll(exercises) }
        fun build() = Workout(
                userUuid = userUuid,
                note = note,
                exercises = exercises,
                date = date
        )
    }

    fun getTotalVolume(): Int {
        var volume: Int = 0;
        for (exercise: ExercisesWithSets in exercises!!) {
            for (set: Set in exercise.sets!!) {
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