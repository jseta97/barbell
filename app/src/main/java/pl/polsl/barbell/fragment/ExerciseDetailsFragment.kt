package pl.polsl.barbell.fragment

import android.os.Bundle
import android.view.*
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import pl.polsl.barbell.R
import pl.polsl.barbell.databinding.FragmentExerciseDetailsBinding
import pl.polsl.barbell.viewModel.ExerciseDetailsViewModel

/**
 * Exercise details fragment
 *
 * @constructor Create empty Exercise details fragment
 */
class ExerciseDetailsFragment() : Fragment() {

    protected var _binding: FragmentExerciseDetailsBinding? = null
    protected val binding get() = _binding!!

    private val exerciseDetailsViewModel: ExerciseDetailsViewModel by activityViewModels()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExerciseDetailsBinding.inflate(inflater, container, false)
        observeViewModel(requireArguments().getString("uuid")!!)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            findNavController().navigate(R.id.action_exerciseDetailsFragment_to_navigation_exercises)
        }
    }

    /**
     * Observe view model
     *
     * @param uuid
     */
    protected fun observeViewModel(uuid: String) {
        exerciseDetailsViewModel.getExercise(uuid)
        exerciseDetailsViewModel.exercise.observe(viewLifecycleOwner) {
            binding.exercise = it
        }
    }

}