package pl.polsl.barbell.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pl.polsl.barbell.model.Exercise
import pl.polsl.barbell.repository.FirestoreProvider

/**
 * Exercise details view model
 *
 * @constructor Create empty Exercise details view model
 */
class ExerciseDetailsViewModel : ViewModel(){

    private val _exercise = MutableLiveData<Exercise>()
    val exercise: LiveData<Exercise>
        get() = _exercise

    /**
     * Get exercise
     *
     * @param uuid
     */
    fun getExercise(uuid: String) {
        FirestoreProvider.instance.getExercise(uuid) {
            _exercise.postValue(it?.first())
        }
    }
}