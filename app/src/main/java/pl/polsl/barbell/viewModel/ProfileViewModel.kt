package pl.polsl.barbell.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import pl.polsl.barbell.model.User
import pl.polsl.barbell.model.Workout
import pl.polsl.barbell.repository.FirestoreProvider

class ProfileViewModel : ViewModel() {

    private val _authenticatedUser = MutableLiveData<User>()
    val authenticatedUser: LiveData<User>
        get() = _authenticatedUser

    private val _workoutCount = MutableLiveData<Int>()
    val workoutCount: LiveData<Int>
        get() = _workoutCount

    private val _points = MutableLiveData<Int>()
    val points: LiveData<Int>
        get() = _points

    fun setUser(uuid: String, callback: () -> Any?) {
        FirestoreProvider.instance.listenForUser(uuid) {
            if (it != null) {
                _authenticatedUser.value = it
            } else {
                _authenticatedUser.value = null
            }
            callback()
        }
    }

    fun setUserDetails(uuid: String) {
        if (_authenticatedUser.value == null) {
            val user = User.Builder(uuid, Firebase.auth.currentUser?.email.toString()).build()
            _authenticatedUser.postValue(user)
        } else {
            FirestoreProvider.instance.getUser(uuid) {
                it?.let { _authenticatedUser.postValue(it) }
            }
        }
    }

    /**
     * @param user - user with proper uuid to be saved
     * @param isFresh - [true] if user is being saved first time, [false] if user is being updated
     */

    fun saveUser(user: User) {
        FirestoreProvider.instance.updateUser(user)
        _authenticatedUser.postValue(user)
    }

    fun signOut() {
        Firebase.auth.signOut()
    }

    fun getWorkoutCount(userUuid: String) {
        FirestoreProvider.instance.getUserWorkouts(userUuid) {
            _workoutCount.postValue(it?.size)
        }
    }

    fun getPoints(userUuid: String){
        FirestoreProvider.instance.getUserWorkouts(userUuid) {
            var p = 0
            for (worout : Workout in it!!){
                p = p.plus(worout.getTotalVolume())
            }
            _points.postValue(p)
        }
    }
}