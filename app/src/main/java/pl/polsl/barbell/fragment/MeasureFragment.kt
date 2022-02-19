package pl.polsl.barbell.fragment

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import pl.polsl.barbell.R
import pl.polsl.barbell.adapter.WeightAdapter
import pl.polsl.barbell.viewModel.MeasureViewModel
import pl.polsl.barbell.databinding.FragmentMeasureBinding
import pl.polsl.barbell.model.Weight
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

/**
 * Measure fragment
 *
 * @constructor Create empty Measure fragment
 */
class MeasureFragment : Fragment() {

    protected var _binding: FragmentMeasureBinding? = null
    protected val binding get() = _binding!!

    private val measureViewModel: MeasureViewModel by activityViewModels()

    private val firebaseAuth: FirebaseAuth by lazy { Firebase.auth }

    private val adapter = WeightAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMeasureBinding.inflate(inflater, container, false)

        observeViewModel()
        binding.weightList.layoutManager =
            GridLayoutManager(context, 1, RecyclerView.VERTICAL, false)
        binding.weightList.adapter = adapter

        binding.swipeRefresh.setOnRefreshListener {
            measureViewModel.getWeightList(firebaseAuth.currentUser!!.uid)
            binding.swipeRefresh.isRefreshing = false
        }
        binding.addWeight.setOnClickListener {
            measureViewModel.addWeight(createWeight())
            Toast.makeText(context, "Added", Toast.LENGTH_SHORT).show()
        }
        return binding.root
    }

    private fun createWeight(): Weight {
        return Weight.Builder(
            firebaseAuth.currentUser!!.uid,
            binding.weightValue.text.toString().toDouble(),
            getDateWithoutTimeUsingFormat()
        ).build()
    }

    /**
     * Get date without time using format
     *
     * @return
     */
    @Throws(ParseException::class)
    fun getDateWithoutTimeUsingFormat(): Date? {
        val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ssZ")
        return formatter.parse(formatter.format(Date()))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.measure_menu, menu)
    }

    /**
     * Observe view model
     *
     */
    protected fun observeViewModel() {
        measureViewModel.getWeightList(firebaseAuth.currentUser!!.uid)
        measureViewModel.weightList.observe(viewLifecycleOwner) {
            reverseList(getSortedList(it)!!)?.let { it1 -> adapter.updateWeightList(it1) }
            childFragmentManager.commit {
                val fragment = WeightChartFragment(getSortedList(it) as ArrayList<Weight>)
                add(R.id.weight_chart, fragment)
            }
        }
    }


    /**
     * Reverse list
     *
     * @param list
     * @return
     */
    fun reverseList(list: List<Weight>): List<Weight> {
        return list.reversed()
    }

    /**
     * Get sorted list
     *
     * @param list
     * @return
     */
    fun getSortedList(list: List<Weight>): List<Weight>? {
        var sortedList : MutableList<Weight> = mutableListOf()
        sortedList.addAll(list)
        sortedList.sortWith(Comparator{ x, y -> x.date?.time!!.compareTo(y.date?.time!!) })
        return sortedList
    }

}