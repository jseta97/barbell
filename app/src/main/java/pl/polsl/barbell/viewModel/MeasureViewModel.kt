package pl.polsl.barbell.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MeasureViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is measure Fragment"
    }
    val text: LiveData<String> = _text
}