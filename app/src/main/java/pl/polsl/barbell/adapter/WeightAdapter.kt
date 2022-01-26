package pl.polsl.barbell.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pl.polsl.barbell.R
import pl.polsl.barbell.model.Weight

class WeightAdapter(
    private val weightList: ArrayList<Weight>
) :
    RecyclerView.Adapter<WeightViewHolder>(), ItemClickListener {


    fun updateWeightList(newWeightList: List<Weight>) {
        weightList.clear()
        weightList.addAll(newWeightList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeightViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.weight_row_item, parent, false)
        return WeightViewHolder(view)
    }

    override fun onBindViewHolder(holder: WeightViewHolder, position: Int) {
        holder.binding.weight = weightList[position]
    }

    override fun getItemCount(): Int = weightList.size

    override fun onClicked(v: View, uuid: String) {
        //TODO: Not yet implemented
    }
}