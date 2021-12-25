package pl.polsl.barbell.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pl.polsl.barbell.R
import pl.polsl.barbell.model.ExercisesWithSets

class ExercisesWithSetsAdapter(private val exercisesList: ArrayList<ExercisesWithSets>) :
        RecyclerView.Adapter<ExercisesWithSetsViewHolder>(), ItemClickListener {

    fun updateExercisesList(newExercisesList: List<ExercisesWithSets>) {
        exercisesList.clear()
        exercisesList.addAll(newExercisesList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExercisesWithSetsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.exercise_with_set_row_item, parent, false)
        return ExercisesWithSetsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExercisesWithSetsViewHolder, position: Int) {
        holder.binding.exercisesWithSets = exercisesList[position]
        holder.binding.listener = this
    }

    override fun getItemCount(): Int = exercisesList.size

    override fun onClicked(v: View, fixUuid: String) {
        //TODO: Not yet implemented
    }
}