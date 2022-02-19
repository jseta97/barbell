package pl.polsl.barbell.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import pl.polsl.barbell.R
import pl.polsl.barbell.model.Exercise

/**
 * Exercises adapter
 *
 * @property exercisesList
 * @constructor Create empty Exercises adapter
 */
class ExercisesAdapter(private val exercisesList: ArrayList<Exercise>) :
        RecyclerView.Adapter<ExerciseViewHolder>(), ItemClickListener {

    var navController: NavController? = null

    /**
     * Update exercises list
     *
     * @param newExercisesList
     */
    fun updateExercisesList(newExercisesList: List<Exercise>) {
        exercisesList.clear()
        exercisesList.addAll(newExercisesList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.exercise_row_item, parent, false)
        navController = parent.findNavController()
        return ExerciseViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        holder.binding.exercise = exercisesList[position]
        holder.binding.listener = this
    }

    override fun getItemCount(): Int = exercisesList.size

    override fun onClicked(v: View, uuid: String) {
        val bundle = Bundle()
        bundle.putString("uuid", uuid)
        navController?.navigate(R.id.action_navigation_exercises_to_exerciseDetailsFragment, bundle)

    }
}