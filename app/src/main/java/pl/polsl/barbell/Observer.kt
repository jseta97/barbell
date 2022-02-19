package pl.polsl.barbell

/**
 * Observer
 *
 * @constructor Create empty Observer
 */
interface Observer {
    /**
     * Update
     *
     * @param position
     */
    fun update(position: Int)
}