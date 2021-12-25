package pl.polsl.barbell.adapter

import android.view.View

interface ItemClickListener {
    fun onClicked(v: View, uuid: String)
}