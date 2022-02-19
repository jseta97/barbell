package pl.polsl.barbell.model

import com.google.firebase.firestore.DocumentId
import java.util.*
import kotlin.collections.ArrayList

/**
 * Workout
 *
 * @property uuid
 * @property userUuid
 * @property note
 * @property exercises
 * @property date
 * @constructor Create empty Workout
 */
class Workout(
        @DocumentId
        val uuid: String? = null,
        val userUuid: String? = null,
        val note: String? = null,
        val exercises: ArrayList<ExercisesWithSets>? = null,
        val date: Date? = null
) {
    /**
     * Builder
     *
     * @property userUuid
     * @property note
     * @property exercises
     * @property date
     * @constructor Create empty Builder
     */
    data class Builder(
            private var userUuid: String?,
            private val note: String,
            private var exercises: ArrayList<ExercisesWithSets> = arrayListOf(),
            private var date: Date? = null
    ) {
        /**
         * User uuid
         *
         * @param userUuid
         */
        fun userUuid(userUuid: String?) = apply { this.userUuid = userUuid }

        /**
         * Date
         *
         * @param date
         */
        fun date(date: Date?) = apply { this.date = date }

        /**
         * Exercises
         *
         * @param exercises
         */
        fun exercises(exercises: List<ExercisesWithSets>) = apply { this.exercises.addAll(exercises) }

        /**
         * Build
         *
         */
        fun build() = Workout(
                userUuid = userUuid,
                note = note,
                exercises = exercises,
                date = date
        )
    }

    /**
     * Get total volume
     *
     * @return
     */
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