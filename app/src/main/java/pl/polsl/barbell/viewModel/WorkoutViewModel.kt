package pl.polsl.barbell.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pl.polsl.barbell.model.Exercise
import pl.polsl.barbell.model.ExercisesWithSets
import pl.polsl.barbell.model.Workout
import pl.polsl.barbell.repository.FirestoreProvider

class WorkoutViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is workout Fragment"
    }
    val text: LiveData<String> = _text

    private val _exercises_list = MutableLiveData<ArrayList<ExercisesWithSets>>()
    val exercisesList: LiveData<ArrayList<ExercisesWithSets>>
        get() = _exercises_list

    private val _workout = MutableLiveData<Workout>()
    val workout: LiveData<Workout>
        get() = _workout

    private val _exercises_list_for_spinner = MutableLiveData<List<Exercise>>()
    val exercisesListForSpinner: LiveData<List<Exercise>>
        get() = _exercises_list_for_spinner

    fun getExercisesForSpinner() {
        FirestoreProvider.instance.getExercises() {
            _exercises_list_for_spinner.postValue(it)
        }
    }

    fun addWorkout(workout: Workout) {
        FirestoreProvider.instance.addWorkout(workout)
    }

    fun addExercise(exercisesWithSets: ExercisesWithSets) {
        val exercises: ArrayList<ExercisesWithSets> = arrayListOf()
        if (getExercises() != null) {
            exercises.addAll(getExercises()!!)
        }
        exercises.add(exercisesWithSets)
        _exercises_list.postValue(exercises)
    }

    fun removeExercise(position: Int){
        if (getExercises() != null) {
            getExercises()?.removeAt(position)
        }
        _exercises_list.postValue(getExercises())
    }

    fun initEmptyExercisesList() {
        _exercises_list.postValue(arrayListOf())
    }

    fun getExercises(): ArrayList<ExercisesWithSets>? {
        return exercisesList.value
    }
}