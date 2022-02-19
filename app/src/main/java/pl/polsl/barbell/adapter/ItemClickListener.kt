package pl.polsl.barbell.adapter

import android.view.View

/**
 * Item click listener
 *
 * @constructor Create empty Item click listener
 */
interface ItemClickListener {
    /**
     * On clicked
     *
     * @param v
     * @param uuid
     */
    fun onClicked(v: View, uuid: String)
}