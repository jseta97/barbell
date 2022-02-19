package pl.polsl.barbell.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import pl.polsl.barbell.databinding.ExerciseRowItemBinding

/**
 * Exercise view holder
 *
 * @constructor
 *
 * @param itemView
 */
class ExerciseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val binding: ExerciseRowItemBinding by lazy { ExerciseRowItemBinding.bind(itemView) }
}