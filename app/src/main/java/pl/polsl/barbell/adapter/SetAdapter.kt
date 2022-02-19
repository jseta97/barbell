package pl.polsl.barbell.adapter

import pl.polsl.barbell.R
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pl.polsl.barbell.model.Set
import java.lang.NumberFormatException


/**
 * Set adapter
 *
 * @property setsList
 * @constructor Create empty Set adapter
 */
class SetAdapter(private val setsList: ArrayList<Set>) : RecyclerView.Adapter<SetAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.set_row_item, parent, false)
        return ViewHolder(view, RepsEditTextListener(), LoadEditTextListener())
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.repsEditTextListener.updatePosition(holder.adapterPosition)
        holder.loadEditTextListener.updatePosition(holder.adapterPosition)
        holder.setTextView.text = setsList[holder.adapterPosition].setCounter.toString()
        if(setsList[holder.adapterPosition].reps.toString() != "null") {
            holder.repsTextView.setText(setsList[holder.adapterPosition].reps.toString())
        }
        if(setsList[holder.adapterPosition].load.toString() != "null") {
            holder.loadTextView.setText(setsList[holder.adapterPosition].load.toString())
        }
        holder.deleteButton.setOnClickListener {
            delete(holder.adapterPosition)
        }
    }

    override fun getItemCount(): Int {
        return setsList.size
    }

    /**
     * Delete
     *
     * @param position
     */
    fun delete(position: Int){
        setsList.removeAt(position)
        setsList.forEachIndexed { index, set ->  set.setCounter=index.plus(1)}
        notifyDataSetChanged()
    }

    /**
     * View holder
     *
     * @constructor
     *
     * @param v
     * @param repsEditTextListener
     * @param loadEditTextListener
     */
    class ViewHolder(v: View, repsEditTextListener: RepsEditTextListener, loadEditTextListener: LoadEditTextListener) : RecyclerView.ViewHolder(v) {
        var repsTextView: EditText
        var loadTextView: EditText
        var setTextView : TextView
        var deleteButton : Button
        var repsEditTextListener: RepsEditTextListener
        var loadEditTextListener: LoadEditTextListener

        init {
            repsTextView = v.findViewById(R.id.repsTextView)
            loadTextView = v.findViewById(R.id.loadTextView)
            setTextView = v.findViewById(R.id.setTextView)
            deleteButton = v.findViewById(R.id.delete_button)
            this.repsEditTextListener = repsEditTextListener
            this.loadEditTextListener = loadEditTextListener
            repsTextView.addTextChangedListener(repsEditTextListener)
            loadTextView.addTextChangedListener(loadEditTextListener)
        }
    }

    /**
     * Reps edit text listener
     *
     * @constructor Create empty Reps edit text listener
     */
    inner class RepsEditTextListener : TextWatcher {
        private var position = 0

        /**
         * Update position
         *
         * @param position
         */
        fun updatePosition(position: Int) {
            this.position = position
        }

        override fun beforeTextChanged(charSequence: CharSequence, i: Int, i2: Int, i3: Int) {

        }

        override fun onTextChanged(charSequence: CharSequence, i: Int, i2: Int, i3: Int) {
            // setsList[position].reps = charSequence.toString().toInt()
        }

        override fun afterTextChanged(editable: Editable) {
            try {
                if ("" != editable.toString() && "null" != editable.toString()) {
                    setsList[position].reps = editable.toString().toInt()
                }
            } catch (e: NumberFormatException) {
                Log.w("SetAdapter", "Wrong data", e)
            }
        }
    }

    /**
     * Load edit text listener
     *
     * @constructor Create empty Load edit text listener
     */
    inner class LoadEditTextListener : TextWatcher {
        private var position = 0

        /**
         * Update position
         *
         * @param position
         */
        fun updatePosition(position: Int) {
            this.position = position
        }

        override fun beforeTextChanged(charSequence: CharSequence, i: Int, i2: Int, i3: Int) {

        }

        override fun onTextChanged(charSequence: CharSequence, i: Int, i2: Int, i3: Int) {
            // setsList[position].load = charSequence.toString().toInt()
        }

        override fun afterTextChanged(editable: Editable) {
            try {
                if ("" != editable.toString() && "null" != editable.toString()) {
                    setsList[position].load = editable.toString().toInt()
                }
            } catch (e: NumberFormatException) {
                Log.w("SetAdapter", "Wrong data", e)
            }
        }
    }
}