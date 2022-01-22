package pl.polsl.barbell.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pl.polsl.barbell.model.Exercise
import pl.polsl.barbell.repository.FirestoreProvider

class ExerciseDetailsViewModel : ViewModel(){

    private val _exercise = MutableLiveData<Exercise>()
    val exercise: LiveData<Exercise>
        get() = _exercise

    fun getExercise(uuid: String) {
        FirestoreProvider.instance.getExercise(uuid) {
            _exercise.postValue(it)
        }
    }
}