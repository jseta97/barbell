package pl.polsl.barbell.fragment

import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import pl.polsl.barbell.R
import pl.polsl.barbell.adapter.WorkoutAdapter
import pl.polsl.barbell.databinding.FragmentWorkoutBinding
import pl.polsl.barbell.model.Exercise
import pl.polsl.barbell.model.ExercisesWithSets
import pl.polsl.barbell.model.Set
import pl.polsl.barbell.model.Workout
import pl.polsl.barbell.viewModel.WorkoutViewModel
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.random.Random

class WorkoutFragment : Fragment() {

    protected var _binding: FragmentWorkoutBinding? = null
    protected val binding get() = _binding!!

    private val workoutViewModel: WorkoutViewModel by activityViewModels()

    private val firebaseAuth: FirebaseAuth by lazy { Firebase.auth }

    private val adapter = WorkoutAdapter(arrayListOf())

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWorkoutBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        observeViewModel()
        binding.exercisesList.layoutManager =
            GridLayoutManager(context, 1, RecyclerView.VERTICAL, false)
        binding.exercisesList.adapter = adapter
        binding.startWorkoutButton.setOnClickListener {
            workoutViewModel.initEmptyExercisesList()
        }
        binding.addExerciseButton.setOnClickListener {
            workoutViewModel.addExercise(generateRandom())
        }
        binding.saveWorkoutButton.setOnClickListener {
            workoutViewModel.addWorkout(createWorkout())
        }
        context?.let {
            ArrayAdapter.createFromResource(
                    it,
                    R.array.planets_array,
                    android.R.layout.simple_spinner_item
            ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            binding.exercisesListSpinner.adapter = adapter
        }
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.workout_menu, menu)
    }

    protected fun observeViewModel() {
        workoutViewModel.exercisesList.observe(viewLifecycleOwner) {
            adapter.updateExercisesList(it)
        }
    }

    fun generateRandom(): ExercisesWithSets {
        return ExercisesWithSets.Builder(
                Exercise.Builder(
                        Random.nextInt(0, 100).toString(),
                        "Squat" + Random.nextInt(0, 100).toString(),
                        "Best exercise",
                        Random.nextInt(0, 100).toString()
                ).build(), arrayListOf(Set.Builder(3, 50).build(), Set.Builder(4, 30).build())
        ).build()
    }

    fun createWorkout(): Workout {
        return Workout.Builder(
                firebaseAuth.currentUser!!.uid,
                "userUuid",
                binding.noteTextView.text.toString(),
                workoutViewModel.exercisesList.value!!,
                getDateWithoutTimeUsingFormat()
        ).build()
    }

    @Throws(ParseException::class)
    fun getDateWithoutTimeUsingFormat(): Date? {
        val formatter = SimpleDateFormat("dd/MM/yyyy")
        return formatter.parse(formatter.format(Date()))
    }
}