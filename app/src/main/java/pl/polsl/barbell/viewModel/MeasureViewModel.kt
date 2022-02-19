package pl.polsl.barbell.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pl.polsl.barbell.model.Exercise
import pl.polsl.barbell.model.Weight
import pl.polsl.barbell.repository.FirestoreProvider

/**
 * Measure view model
 *
 * @constructor Create empty Measure view model
 */
class MeasureViewModel : ViewModel() {

    private val _weight_list = MutableLiveData<List<Weight>>()
    val weightList: LiveData<List<Weight>>
        get() = _weight_list

    /**
     * Get weight list
     *
     * @param userUuid
     */
    fun getWeightList(userUuid: String) {
        FirestoreProvider.instance.getWeightList(userUuid) {
            _weight_list.postValue(it)
        }
    }

    /**
     * Add weight
     *
     * @param weight
     */
    fun addWeight(weight: Weight) {
        FirestoreProvider.instance.addWeight(weight)
    }

}