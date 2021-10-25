package pl.polsl.barbell.ui.exercises

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import pl.polsl.barbell.R

class ExercisesFragment : Fragment() {

    private lateinit var exercisesViewModel: ExercisesViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        exercisesViewModel =
                ViewModelProvider(this).get(ExercisesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_exercises, container, false)
        val textView: TextView = root.findViewById(R.id.text_exercises)
        exercisesViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
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
                // navigate to add screen
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}