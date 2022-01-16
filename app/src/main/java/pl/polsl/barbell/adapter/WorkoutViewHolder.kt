package pl.polsl.barbell.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import pl.polsl.barbell.databinding.WorkoutRowItemBinding

class WorkoutViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val binding: WorkoutRowItemBinding by lazy { WorkoutRowItemBinding.bind(itemView) }
}