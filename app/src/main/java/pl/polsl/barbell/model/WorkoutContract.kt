package pl.polsl.barbell.model

object WorkoutContract {
    internal const val COLLECTION_NAME = "Workouts"

    object Fields {
        const val UUID = "uuid"
        const val USERUUID = "useruuid"
        const val NOTE = "note"
        const val EXERCISES = "exercises"
        const val DATE = "date"
    }
}