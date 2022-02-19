package pl.polsl.barbell.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import pl.polsl.barbell.model.User
import pl.polsl.barbell.model.Workout
import pl.polsl.barbell.repository.FirestoreProvider

/**
 * Profile view model
 *
 * @constructor Create empty Profile view model
 */
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

    /**
     * Set user
     *
     * @param uuid
     * @param callback
     * @receiver
     */
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

    /**
     * Set user details
     *
     * @param uuid
     */
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
     * Save user
     *
     * @param user
     */

    fun saveUser(user: User) {
        FirestoreProvider.instance.updateUser(user)
        _authenticatedUser.postValue(user)
    }

    /**
     * Sign out
     *
     */
    fun signOut() {
        Firebase.auth.signOut()
    }

    /**
     * Get workout count
     *
     * @param userUuid
     */
    fun getWorkoutCount(userUuid: String) {
        FirestoreProvider.instance.getUserWorkouts(userUuid) {
            _workoutCount.postValue(it?.size)
        }
    }

    /**
     * Get points
     *
     * @param userUuid
     */
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