package pl.polsl.barbell.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.data2viz.charts.chart.Chart
import io.data2viz.charts.chart.chart
import io.data2viz.charts.chart.discrete
import io.data2viz.charts.chart.mark.area
import io.data2viz.charts.chart.quantitative
import io.data2viz.geom.Size
import io.data2viz.viz.VizContainerView
import pl.polsl.barbell.model.Weight

class WeightChartFragment(private val list: ArrayList<Weight>) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return CanadaChart(requireContext(), list)
    }

    class CanadaChart(context: Context, private val list: ArrayList<Weight>) : VizContainerView(context) {
        val vizSize = 500.0
        private val chart: Chart<Weight> = chart(list) {
            size = Size(vizSize, vizSize)
            title = "Weight (kg)"
            // Create a discrete dimension for the date of measure
            val date = discrete({ "Day: ".plus(domain.date?.date.toString())
                .plus("/").plus(domain.date?.hours.toString())
                .plus(":").plus(domain.date?.minutes.toString())
                .plus(":").plus(domain.date?.seconds.toString())
            })
            // Create a continuous numeric dimension for the weight
            val weight = quantitative({ domain.value }) {
                name = "Weight (kg)"
            }
            // Using a discrete dimension for the X-axis and a continuous one for the Y-axis
            area(date, weight)
        }

        override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
            super.onSizeChanged(w, h, oldw, oldh)
            chart.size = Size(vizSize, vizSize * h / w)
        }
    }
}
