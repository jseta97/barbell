package pl.polsl.barbell.model

import kotlin.collections.ArrayList

/**
 * Exercises with sets
 *
 * @property exercise
 * @property sets
 * @constructor Create empty Exercises with sets
 */
class ExercisesWithSets(
        val exercise: Exercise? = null,
        val sets: ArrayList<Set>? = null
) {
    /**
     * Builder
     *
     * @property exercise
     * @property sets
     * @constructor Create empty Builder
     */
    data class Builder(
            private var exercise: Exercise? = null,
            private var sets: ArrayList<Set> = arrayListOf(),
    ) {
        /**
         * Sets
         *
         * @param sets
         */
        fun sets(sets: ArrayList<Set>) = apply { this.sets = sets }

        /**
         * Exercise
         *
         * @param exercise
         */
        fun exercise(exercise: Exercise) = apply { this.exercise = exercise }

        /**
         * Build
         *
         */
        fun build() = ExercisesWithSets(
                exercise,
                sets
        )
    }

    /**
     * Get volume
     *
     * @return
     */
    fun getVolume(): Int {
        var volume: Int = 0;
        for(set: Set in sets!!){
            if(set.load==null){
                set.load=0
            }
            if(set.reps==null){
                set.reps=0
            }
            volume+=set.load!! * set.reps!!
        }
        return volume;
    }

    /**
     * Get max load
     *
     * @return
     */
    fun getMaxLoad(): Int {
        var max: Int = 0;
        for(set: Set in sets!!){
            if(set.load!! > max){
               max = set.load!!
            }
        }
        return max;
    }
}