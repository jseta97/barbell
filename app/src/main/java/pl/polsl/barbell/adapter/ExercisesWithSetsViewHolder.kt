package pl.polsl.barbell.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import pl.polsl.barbell.databinding.ExerciseRowItemBinding
import pl.polsl.barbell.databinding.ExerciseWithSetRowItemBinding

/**
 * Exercises with sets view holder
 *
 * @constructor
 *
 * @param itemView
 */
class ExercisesWithSetsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val binding: ExerciseWithSetRowItemBinding by lazy { ExerciseWithSetRowItemBinding.bind(itemView) }
}