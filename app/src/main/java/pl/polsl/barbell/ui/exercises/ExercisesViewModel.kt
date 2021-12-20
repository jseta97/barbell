package pl.polsl.barbell.ui.exercises

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pl.polsl.barbell.model.Exercise

class ExercisesViewModel : ViewModel() {

    private val _exercises_list = MutableLiveData<List<Exercise>>()
    val exercisesList: LiveData<List<Exercise>>
        get() = _exercises_list

    fun getExercises() {
        /*FirestoreProvider.instance.getExercises() {
            _exercises_list.postValue(it)
        }*/
    }

    fun addExercise(exercise: List<Exercise>) {
        _exercises_list.postValue(exercise)
    }
}