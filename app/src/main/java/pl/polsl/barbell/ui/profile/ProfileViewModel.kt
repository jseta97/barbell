package pl.polsl.barbell.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import pl.polsl.barbell.repository.FirestoreProvider

class ProfileViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is profile Fragment"
    }
    val text: LiveData<String> = _text

    private val _authenticatedUser = MutableLiveData<pl.polsl.barbell.model.User>()
    val authenticatedUser: LiveData<pl.polsl.barbell.model.User>
        get() = _authenticatedUser

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

    fun signOut() {
        Firebase.auth.signOut()
    }
}