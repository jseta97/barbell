package pl.polsl.barbell.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import pl.polsl.barbell.databinding.HistoryRowItemBinding

/**
 * History view holder
 *
 * @constructor
 *
 * @param itemView
 */
class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val binding: HistoryRowItemBinding by lazy { HistoryRowItemBinding.bind(itemView) }
}