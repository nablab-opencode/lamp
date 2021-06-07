package ar.nablab.mimerendero.viewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.nablab.mimerendero.dtos.Place
import ar.nablab.mimerendero.repositories.MapRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(private val mapRepository: MapRepository): ViewModel() {

    private var places : MutableLiveData<ArrayList<Place>> = MutableLiveData<ArrayList<Place>>()

    fun getPlacesList() : MutableLiveData<ArrayList<Place>> {

        viewModelScope.launch {
            places.value = mapRepository.getPlaces()

            Log.d(TAG, "Places: ${places.value}")
        }
            return places
    }

    fun savePlace(place: Place) {
        mapRepository.savePlace(place)
    }

    companion object {
        val TAG: String = MapViewModel::class.java.simpleName
    }
}
