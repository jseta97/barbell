package pl.polsl.barbell.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import pl.polsl.barbell.databinding.SetRowItemBinding

class SetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val binding: SetRowItemBinding by lazy { SetRowItemBinding.bind(itemView) }
}