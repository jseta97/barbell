package pl.polsl.barbell.fragment

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import pl.polsl.barbell.R
import pl.polsl.barbell.viewModel.MeasureViewModel

class MeasureFragment : Fragment() {

    private lateinit var measureViewModel: MeasureViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        measureViewModel =
                ViewModelProvider(this).get(MeasureViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_measure, container, false)
        val textView: TextView = root.findViewById(R.id.text_measure)
        measureViewModel.text.observe(viewLifecycleOwner, {
            textView.text = it
        })
        return root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.measure_menu, menu)
    }
}