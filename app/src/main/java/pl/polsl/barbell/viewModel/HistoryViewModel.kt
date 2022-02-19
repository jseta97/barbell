package pl.polsl.barbell.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pl.polsl.barbell.model.Workout
import pl.polsl.barbell.repository.FirestoreProvider

/**
 * History view model
 *
 * @constructor Create empty History view model
 */
class HistoryViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is history Fragment"
    }
    val text: LiveData<String> = _text

    private val _workout_list = MutableLiveData<List<Workout>>()
    val workoutList: LiveData<List<Workout>>
        get() = _workout_list

    /**
     * Get workouts
     *
     */
    fun getWorkouts() {
        FirestoreProvider.instance.getWorkouts() {
            _workout_list.postValue(it)
        }
    }

    /**
     * Get workouts
     *
     * @param userUuid
     */
    fun getWorkouts(userUuid: String) {
        FirestoreProvider.instance.getUserWorkouts(userUuid) {
            _workout_list.postValue(it)
        }
    }

    /**
     * Add workout
     *
     * @param workout
     */
    fun addWorkout(workout: Workout) {
        FirestoreProvider.instance.addWorkout(workout)
    }
}