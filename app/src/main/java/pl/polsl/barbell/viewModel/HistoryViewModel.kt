package pl.polsl.barbell.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pl.polsl.barbell.model.Workout
import pl.polsl.barbell.repository.FirestoreProvider

class HistoryViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is history Fragment"
    }
    val text: LiveData<String> = _text

    private val _workout_list = MutableLiveData<List<Workout>>()
    val workoutList: LiveData<List<Workout>>
        get() = _workout_list

    fun getWorkouts() {
        FirestoreProvider.instance.getWorkouts() {
            _workout_list.postValue(it)
        }
    }

    fun getWorkouts(userUuid: String) {
        FirestoreProvider.instance.getUserWorkouts(userUuid) {
            _workout_list.postValue(it)
        }
    }

    fun addWorkout(workout: Workout) {
        FirestoreProvider.instance.addWorkout(workout)
    }
}