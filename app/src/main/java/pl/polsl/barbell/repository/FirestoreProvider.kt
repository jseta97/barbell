package pl.polsl.barbell.repository

import android.util.Log
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import pl.polsl.barbell.model.*

private const val TAG = "FirestoreProvider"

/**
 * Firestore provider
 *
 * @constructor Create empty Firestore provider
 */
class FirestoreProvider private constructor() {

    private val db = Firebase.firestore
    private val storage = FirebaseStorage.getInstance()

    /**
     * Add user
     *
     * @param user
     */
    fun addUser(user: User) {
        db.collection(UsersContract.COLLECTION_NAME).document(user.uuid!!).set(user)
    }

    /**
     * Get user
     *
     * @param uuid
     * @param callback
     * @receiver
     */
    fun getUser(uuid: String, callback: (User?) -> Unit) {
        db.collection(UsersContract.COLLECTION_NAME).document(uuid).get().addOnCompleteListener {
            var user: User? = null
            if (it.isSuccessful) {
                user = it.result?.toObject<User>()
            } else {
                Log.w(TAG, "getUser() failed.", it.exception?.cause)
            }
            callback(user)
        }
    }

    /**
     * Update user
     *
     * @param user
     */
    fun updateUser(user: User) {
        db.collection(UsersContract.COLLECTION_NAME).document(user.uuid!!).get().addOnCompleteListener {
            if (it.isSuccessful) {
                db.collection(UsersContract.COLLECTION_NAME)
                        .document(user.uuid)
                        .set(user, SetOptions.merge())
            } else {
                addUser(user)
                Log.d(TAG, "Object not found", it.exception)
            }
        }
    }

    /**
     * Listen for user
     *
     * @param uuid
     * @param callback
     * @receiver
     */
    fun listenForUser(uuid: String, callback: (User?) -> Unit) {
        db.collection(UsersContract.COLLECTION_NAME)
                .document(uuid)
                .addSnapshotListener { value, error ->
                    if (error != null) {
                        Log.w(TAG, "Listen failed.", error)
                        callback(null)
                        return@addSnapshotListener
                    } else {
                        callback(value?.toObject<User>())
                    }
                }
    }


    /**
     * Add weight
     *
     * @param weight
     */
    fun addWeight(weight: Weight) {
        db.collection(WeightContract.COLLECTION_NAME).add(weight)
    }

    /**
     * Get weight list
     *
     * @param userUuid
     * @param callback
     * @receiver
     */
    fun getWeightList(userUuid: String, callback: (List<Weight>?) -> Unit) {
        db.collection(WeightContract.COLLECTION_NAME)
            .whereEqualTo(WeightContract.Fields.USERUUID, userUuid)
            .get()
            .addOnSuccessListener { documents ->
                val weight : ArrayList<Weight> = ArrayList()
                for (document in documents) {
                    Log.d(TAG, "${document.id} => ${document.data}")
                    weight.add(document.toObject())
                }
                callback(weight)
            }
            .addOnFailureListener {
                Log.w(TAG, "Listen failed.", it.cause)
                callback(null)
            }
    }

    /**
     * Add exercise
     *
     * @param exercise
     */
    fun addExercise(exercise: Exercise) {
        db.collection(ExerciseContract.COLLECTION_NAME).add(exercise)
    }

    /*fun getExercise(uuid: String, callback: (Exercise?) -> Unit) {
        db.collection(ExerciseContract.COLLECTION_NAME).document(uuid).get().addOnCompleteListener {
            var exercise: Exercise? = null
            if (it.isSuccessful) {
                exercise = it.result?.toObject<Exercise>()
            } else {
                Log.w(TAG, "getExercise() failed", it.exception?.cause)
            }
            callback(exercise)
        }
    }*/

    /**
     * Get exercise
     *
     * @param uuid
     * @param callback
     * @receiver
     */
    fun getExercise(uuid: String, callback: (List<Exercise>?) -> Unit) {
        db.collection(ExerciseContract.COLLECTION_NAME)
                .whereEqualTo(ExerciseContract.Fields.UUID, uuid)
                .get()
                .addOnSuccessListener { documents ->
                    val exercises : ArrayList<Exercise> = ArrayList()
                    for (document in documents) {
                        Log.d(TAG, "${document.id} => ${document.data}")
                        exercises.add(document.toObject())
                    }
                    callback(exercises)
                }
                .addOnFailureListener {
                    Log.w(TAG, "Listen failed.", it.cause)
                    callback(null)
                }
    }

    /**
     * Get exercises
     *
     * @param callback
     * @receiver
     */
    fun getExercises(callback: (List<Exercise>?) -> Unit) {
        db.collection(ExerciseContract.COLLECTION_NAME).get().addOnSuccessListener { documents ->
            val exercises: ArrayList<Exercise> = ArrayList()
            for (document in documents) {
                Log.d(TAG, "${document.id} => ${document.data}")
                exercises.add(document.toObject())
            }
            callback(exercises)
        }
                .addOnFailureListener {
                    Log.w(TAG, "Listen failed.", it.cause)
                    callback(null)
                }
    }

    /**
     * Add workout
     *
     * @param workout
     */
    fun addWorkout(workout: Workout) {
        db.collection(WorkoutContract.COLLECTION_NAME).add(workout)
    }

    /**
     * Get workout
     *
     * @param uuid
     * @param callback
     * @receiver
     */
    fun getWorkout(uuid: String, callback: (Workout?) -> Unit) {
        db.collection(WorkoutContract.COLLECTION_NAME).document(uuid).get().addOnCompleteListener {
            var workout: Workout? = null
            if (it.isSuccessful) {
                workout = it.result?.toObject<Workout>()
            } else {
                Log.w(TAG, "getWorkout() failed", it.exception?.cause)
            }
            callback(workout)
        }
    }

    /**
     * Get workouts
     *
     * @param callback
     * @receiver
     */
    fun getWorkouts(callback: (List<Workout>?) -> Unit) {
        db.collection(WorkoutContract.COLLECTION_NAME).get().addOnSuccessListener { documents ->
            val workouts: ArrayList<Workout> = ArrayList()
            for (document in documents) {
                Log.d(TAG, "${document.id} => ${document.data}")
                workouts.add(document.toObject())
            }
            callback(workouts)
        }
                .addOnFailureListener {
                    Log.w(TAG, "Listen failed.", it.cause)
                    callback(null)
                }
    }

    /**
     * Get user workouts
     *
     * @param userUuid
     * @param callback
     * @receiver
     */
    fun getUserWorkouts(userUuid: String, callback: (List<Workout>?) -> Unit) {
        db.collection(WorkoutContract.COLLECTION_NAME)
                .whereEqualTo(WorkoutContract.Fields.USERUUID, userUuid)
                .get()
                .addOnSuccessListener { documents ->
                    val workouts : ArrayList<Workout> = ArrayList()
                    for (document in documents) {
                        Log.d(TAG, "${document.id} => ${document.data}")
                        workouts.add(document.toObject())
                    }
                    callback(workouts)
                }
                .addOnFailureListener {
                    Log.w(TAG, "Listen failed.", it.cause)
                    callback(null)
                }
    }

    private object SingletonHolder {
        val instance = FirestoreProvider()
    }

    companion object {
        val instance: FirestoreProvider by lazy { SingletonHolder.instance }
    }
}