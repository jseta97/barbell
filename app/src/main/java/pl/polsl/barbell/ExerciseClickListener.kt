package pl.polsl.barbell

import android.view.View

interface ExerciseClickListener {
    fun onExClicked(v: View, exUuid: String)
}