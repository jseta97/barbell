package pl.polsl.barbell.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pl.polsl.barbell.model.Exercise
import pl.polsl.barbell.repository.FirestoreProvider

/**
 * Exercises view model
 *
 * @constructor Create empty Exercises view model
 */
class ExercisesViewModel : ViewModel() {

    private val _exercises_list = MutableLiveData<List<Exercise>>()
    val exercisesList: LiveData<List<Exercise>>
        get() = _exercises_list

    /**
     * Get exercises
     *
     */
    fun getExercises() {
        FirestoreProvider.instance.getExercises() {
            _exercises_list.postValue(it)
        }
    }

    /**
     * Add exercise
     *
     * @param exercise
     */
    fun addExercise(exercise: Exercise) {
        FirestoreProvider.instance.addExercise(exercise)
    }
}