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