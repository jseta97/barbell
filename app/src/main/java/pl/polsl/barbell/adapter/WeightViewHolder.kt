package pl.polsl.barbell.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import pl.polsl.barbell.databinding.WeightRowItemBinding
import pl.polsl.barbell.databinding.WorkoutRowItemBinding

class WeightViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val binding: WeightRowItemBinding by lazy { WeightRowItemBinding.bind(itemView) }
}