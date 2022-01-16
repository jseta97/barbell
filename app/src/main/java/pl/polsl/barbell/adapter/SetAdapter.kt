package pl.polsl.barbell.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pl.polsl.barbell.R
import pl.polsl.barbell.model.Set

class SetAdapter(private val setsList: ArrayList<Set>) :
    RecyclerView.Adapter<SetViewHolder>(), ItemClickListener {

    fun updateSetsList(newSetsList: List<Set>) {
        setsList.clear()
        setsList.addAll(newSetsList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SetViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.set_row_item, parent, false)
        return SetViewHolder(view)
    }

    override fun onBindViewHolder(holder: SetViewHolder, position: Int) {
        holder.binding.set = setsList[position]
        holder.binding.listener = this
    }

    override fun getItemCount(): Int = setsList.size

    override fun onClicked(v: View, fixUuid: String) {
        //TODO: Not yet implemented
    }
}