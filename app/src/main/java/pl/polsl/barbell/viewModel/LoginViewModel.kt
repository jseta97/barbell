package pl.polsl.barbell.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

/**
 * Login view model
 *
 * @constructor
 *
 * @param application
 */
class LoginViewModel(application: Application) : AndroidViewModel(application) {

    /**
     * Register result listener
     *
     * @constructor Create empty Register result listener
     */
    interface RegisterResultListener {
        /**
         * On success
         *
         * @param uuid
         */
        fun onSuccess(uuid: String)

        /**
         * On email malformed
         *
         */
        fun onEmailMalformed()

        /**
         * On password too short
         *
         */
        fun onPasswordTooShort()

        /**
         * On password not matched
         *
         */
        fun onPasswordNotMatched()

        /**
         * On user already exists
         *
         */
        fun onUserAlreadyExists()
    }

    /**
     * Login result listener
     *
     * @constructor Create empty Login result listener
     */
    interface LoginResultListener {
        /**
         * On success
         *
         */
        fun onSuccess()

        /**
         * On user not exists
         *
         */
        fun onUserNotExists()

        /**
         * On wrong password
         *
         */
        fun onWrongPassword()

        /**
         * On empty credentials
         *
         */
        fun onEmptyCredentials()
    }

    private val firebaseAuth: FirebaseAuth by lazy { Firebase.auth }

    private lateinit var loginListener: LoginResultListener

    private lateinit var registerListener: RegisterResultListener

    /**
     * Set register result listener
     *
     * @param listener
     */
    fun setRegisterResultListener(listener: RegisterResultListener) {
        registerListener = listener
    }

    /**
     * Set login result listener
     *
     * @param listener
     */
    fun setLoginResultListener(listener: LoginResultListener) {
        loginListener = listener
    }

    /**
     * Sign in with email and password
     *
     * @param email
     * @param password
     */
    fun signInWithEmailAndPassword(email: String, password: String) {
        if (email.isNotEmpty() || password.isNotEmpty()) {
            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful) {
                    loginListener.onSuccess()
                } else {
                    when (it.exception) {
                        is FirebaseAuthInvalidUserException -> loginListener.onUserNotExists()
                        is FirebaseAuthInvalidCredentialsException -> loginListener.onWrongPassword()
                    }
                }
            }
        } else {
            loginListener.onEmptyCredentials()
        }
    }

    /**
     * Register user with email and password
     *
     * @param email
     * @param password
     * @param repeatedPassword
     */
    fun registerUserWithEmailAndPassword(
            email: String,
            password: String,
            repeatedPassword: String
    ) {
        if (comparePassword(
                        password,
                        repeatedPassword
                ) && passwordLength(password) && email.isNotEmpty()
        ) {
            firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful) {
                    registerListener.onSuccess(it.result?.user?.uid.toString())
                } else {
                    when (it.exception) {
                        is FirebaseAuthInvalidCredentialsException -> {
                            registerListener.onEmailMalformed()
                        }
                        is FirebaseAuthUserCollisionException -> {
                            registerListener.onUserAlreadyExists()
                        }
                    }
                }
            }
        } else {
            if (!comparePassword(
                            password,
                            repeatedPassword
                    )
            ) registerListener.onPasswordNotMatched()
            else if (!passwordLength(password)) registerListener.onPasswordTooShort()
            else if (email.isEmpty()) registerListener.onEmailMalformed()
        }
    }

    private fun comparePassword(password: String, repeatedPassword: String): Boolean {
        return password == repeatedPassword
    }

    private fun passwordLength(password: String): Boolean {
        return password.length >= 8
    }
}