package pl.polsl.barbell.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pl.polsl.barbell.R
import pl.polsl.barbell.model.Exercise

class ExercisesAdapter(private val exercisesList: ArrayList<Exercise>) :
        RecyclerView.Adapter<ExerciseViewHolder>(), ItemClickListener {

    fun updateExercisesList(newExercisesList: List<Exercise>) {
        exercisesList.clear()
        exercisesList.addAll(newExercisesList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.exercise_row_item, parent, false)
        return ExerciseViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        holder.binding.exercise = exercisesList[position]
        holder.binding.listener = this
    }

    override fun getItemCount(): Int = exercisesList.size

    override fun onClicked(v: View, fixUuid: String) {
        //TODO: Not yet implemented
    }
}