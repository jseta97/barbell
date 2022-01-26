package pl.polsl.barbell.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pl.polsl.barbell.R
import pl.polsl.barbell.adapter.ExercisesAdapter
import pl.polsl.barbell.databinding.*
import pl.polsl.barbell.model.Exercise
import pl.polsl.barbell.viewModel.ExercisesViewModel
import kotlin.random.Random

class ExercisesFragment : Fragment() {

    protected var _binding: FragmentExercisesBinding? = null
    protected val binding get() = _binding!!

    private val exercisesViewModel: ExercisesViewModel by activityViewModels()

    private val adapter = ExercisesAdapter(arrayListOf())

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExercisesBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        observeViewModel()
        binding.exercisesList.layoutManager =
                GridLayoutManager(context, 1, RecyclerView.VERTICAL, false)
        binding.exercisesList.adapter = adapter

        binding.swipeRefresh.setOnRefreshListener {
            exercisesViewModel.getExercises()
            binding.swipeRefresh.isRefreshing = false
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.exercises_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_search -> {
                // navigate to search screen
                true
            }
            R.id.action_filter -> {
                // navigate to filter screen
                true
            }
            R.id.action_add -> {
                exercisesViewModel.addExercise(generateRandom())
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    protected fun observeViewModel() {
        exercisesViewModel.getExercises()
        exercisesViewModel.exercisesList.observe(viewLifecycleOwner) {
            adapter.updateExercisesList(it)
        }
    }

    fun generateRandom(): Exercise {
        return Exercise.Builder(
                Random.nextInt(0, 100).toString(),
                "Bench" + Random.nextInt(0, 100).toString(),
                "Description" + Random.nextInt(0, 100).toString(),
                "Legs",
                Random.nextInt(0, 100).toString()
        ).build()
    }


}