package pl.polsl.barbell.fragment

import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import pl.polsl.barbell.R
import pl.polsl.barbell.adapter.ExercisesSpinnerAdapter
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

class WorkoutFragment : Fragment(), AdapterView.OnItemSelectedListener {

    protected var _binding: FragmentWorkoutBinding? = null
    protected val binding get() = _binding!!

    private val workoutViewModel: WorkoutViewModel by activityViewModels()

    private val firebaseAuth: FirebaseAuth by lazy { Firebase.auth }

    private val adapter = WorkoutAdapter(arrayListOf(), DeleteExercise())

    private var selectedExercise: Exercise? = null

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWorkoutBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        observeViewModel()
        binding.exercisesListSpinner.onItemSelectedListener = this
        binding.exercisesList.layoutManager =
                GridLayoutManager(context, 1, RecyclerView.VERTICAL, false)
        binding.exercisesList.adapter = adapter
        binding.startWorkoutButton.setOnClickListener {
            workoutViewModel.initEmptyExercisesList()
        }
        binding.addExerciseButton.setOnClickListener {
            workoutViewModel.addExercise(createExercise())
        }
        binding.saveWorkoutButton.setOnClickListener {
            if(workoutViewModel.exercisesList.value!=null) {
                workoutViewModel.addWorkout(createWorkout())
                findNavController().navigate(R.id.navigation_history)
            }else {
                Toast.makeText(context, "Add some exercises", Toast.LENGTH_SHORT).show()
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

    private fun observeViewModel() {
        workoutViewModel.exercisesList.observe(viewLifecycleOwner) {
            adapter.updateExercisesList(it)
        }
        workoutViewModel.getExercisesForSpinner()
        workoutViewModel.exercisesListForSpinner.observe(viewLifecycleOwner) {
            val spinnerAdapter = context?.let { it1 -> ExercisesSpinnerAdapter(it1, it) }
            binding.exercisesListSpinner.adapter = spinnerAdapter
        }
    }

    private fun createExercise(): ExercisesWithSets {
        return ExercisesWithSets.Builder(
                selectedExercise, arrayListOf(Set.Builder(null, null, 1).build())
        ).build()
    }

    private fun createWorkout(): Workout {
        return Workout.Builder(
                firebaseAuth.currentUser!!.uid,
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

    override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
        selectedExercise = parent.getItemAtPosition(pos) as Exercise
    }

    override fun onNothingSelected(parent: AdapterView<*>) {
        selectedExercise = parent.getItemAtPosition(1) as Exercise
    }

    inner class DeleteExercise(){
        fun delete(position: Int){
            workoutViewModel.removeExercise(position)
        }
    }
}