package pl.polsl.barbell.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import pl.polsl.barbell.R
import pl.polsl.barbell.adapter.HistoryAdapter
import pl.polsl.barbell.databinding.FragmentHistoryBinding
import pl.polsl.barbell.model.Exercise
import pl.polsl.barbell.model.ExercisesWithSets
import pl.polsl.barbell.model.Set
import pl.polsl.barbell.model.Workout
import pl.polsl.barbell.viewModel.HistoryViewModel
import java.util.*
import kotlin.random.Random

class HistoryFragment : Fragment() {

    protected var _binding: FragmentHistoryBinding? = null
    protected val binding get() = _binding!!

    private val historyViewModel: HistoryViewModel by activityViewModels()

    private val adapter = HistoryAdapter(arrayListOf())

    private val firebaseAuth: FirebaseAuth by lazy { Firebase.auth }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        observeViewModel()
        binding.workoutList.layoutManager =
                GridLayoutManager(context, 1, RecyclerView.VERTICAL, false)
        binding.workoutList.adapter = adapter

        binding.swipeRefresh.setOnRefreshListener {
            historyViewModel.getWorkouts(firebaseAuth.currentUser!!.uid)
            binding.swipeRefresh.isRefreshing = false
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.history_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_calendar -> {
                // navigate to calendar screen
                historyViewModel.addWorkout(generateRandom())
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    protected fun observeViewModel() {
        historyViewModel.getWorkouts(firebaseAuth.currentUser!!.uid)
        historyViewModel.workoutList.observe(viewLifecycleOwner) {
            adapter.updateWorkoutList(it)
        }
    }

    fun generateRandom(): Workout {
        return Workout.Builder(
                "userUuid",
                "note",
                arrayListOf(ExercisesWithSets.Builder(
                        Exercise.Builder(
                                Random.nextInt(0, 100).toString(),
                                "Squat" + Random.nextInt(0, 100).toString(),
                                "Best exercise",
                                Random.nextInt(0, 100).toString()
                        ).build(), arrayListOf(Set.Builder(3).build(), Set.Builder(4).build())
                ).build(),
                        ExercisesWithSets.Builder(
                                Exercise.Builder(
                                        Random.nextInt(0, 100).toString(),
                                        "Deadlift" + Random.nextInt(0, 100).toString(),
                                        "Best exercise also",
                                        Random.nextInt(0, 100).toString()
                                ).build(), arrayListOf(Set.Builder(4).build(), Set.Builder(5).build(), Set.Builder(6).build(), Set.Builder(7).build())
                        ).build()),
                Calendar.getInstance().time
        ).build()
    }
}