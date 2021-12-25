package pl.polsl.barbell.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.RecycledViewPool
import pl.polsl.barbell.R
import pl.polsl.barbell.model.Workout


class HistoryAdapter(private val workoutList: ArrayList<Workout>) :
        RecyclerView.Adapter<HistoryViewHolder>(), ItemClickListener {

    private var viewPool = RecycledViewPool()

    fun updateWorkoutList(newWorkoutList: List<Workout>) {
        workoutList.clear()
        workoutList.addAll(newWorkoutList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.history_row_item, parent, false)
        return HistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.binding.workout = workoutList[position]
        val adapter = ExercisesWithSetsAdapter(workoutList[position].exercises!!)
        val layoutManager = GridLayoutManager(holder.binding.exercisesListView.context, 1, RecyclerView.VERTICAL, false)
        layoutManager.initialPrefetchItemCount = workoutList[position].exercises!!.size
        holder.binding.exercisesListView.layoutManager = layoutManager
        holder.binding.exercisesListView.adapter = adapter
        holder.binding.exercisesListView.setRecycledViewPool(viewPool)
        holder.binding.listener = this
    }

    override fun getItemCount(): Int = workoutList.size

    override fun onClicked(v: View, uuid: String) {
        //TODO: Not yet implemented
    }
}