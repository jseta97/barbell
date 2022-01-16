package pl.polsl.barbell.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pl.polsl.barbell.R
import pl.polsl.barbell.model.ExercisesWithSets
import pl.polsl.barbell.model.Set
import kotlin.random.Random

class WorkoutAdapter(private val exercisesList: ArrayList<ExercisesWithSets>) :
    RecyclerView.Adapter<WorkoutViewHolder>(), ItemClickListener {

    private var viewPool = RecyclerView.RecycledViewPool()


    fun updateExercisesList(newExercisesList: List<ExercisesWithSets>) {
        exercisesList.clear()
        exercisesList.addAll(newExercisesList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkoutViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.workout_row_item, parent, false)
        return WorkoutViewHolder(view)
    }

    override fun onBindViewHolder(holder: WorkoutViewHolder, position: Int) {
        holder.binding.exercisesWithSets = exercisesList[position]
        holder.binding.addSetButton.setOnClickListener {
            addSet(generateRandom(), position)
            val adapter = SetAdapter(exercisesList[position].sets!!)
            val layoutManager = GridLayoutManager(
                holder.binding.setsListView.context,
                1,
                RecyclerView.VERTICAL,
                false
            )
            layoutManager.initialPrefetchItemCount = exercisesList[position].sets!!.size
            holder.binding.setsListView.layoutManager = layoutManager
            holder.binding.setsListView.adapter = adapter
            holder.binding.setsListView.setRecycledViewPool(viewPool)
            holder.binding.listener = this
        }
        val adapter = SetAdapter(exercisesList[position].sets!!)
        val layoutManager =
            GridLayoutManager(holder.binding.setsListView.context, 1, RecyclerView.VERTICAL, false)
        layoutManager.initialPrefetchItemCount = exercisesList[position].sets!!.size
        holder.binding.setsListView.layoutManager = layoutManager
        holder.binding.setsListView.adapter = adapter
        holder.binding.setsListView.setRecycledViewPool(viewPool)
        holder.binding.listener = this
    }

    override fun getItemCount(): Int = exercisesList.size

    override fun onClicked(v: View, uuid: String) {
        //TODO: Not yet implemented
    }

    fun generateRandom(): Set {
        return Set.Builder(Random.nextInt(0, 100), Random.nextInt(0, 100)).build()
    }


    fun addSet(set: Set, position: Int) {
        val sets: ArrayList<Set> = arrayListOf()
        if (exercisesList[position].sets != null) {
            sets.addAll(exercisesList[position].sets!!)
        }
        sets.add(set)
        sets.let {
            exercisesList[position].sets?.clear()
            exercisesList[position].sets?.addAll(it)
        }
    }
}