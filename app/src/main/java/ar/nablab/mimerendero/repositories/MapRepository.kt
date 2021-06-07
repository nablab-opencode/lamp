package ar.nablab.mimerendero.repositories

import ar.nablab.mimerendero.dtos.Place
import java.util.ArrayList

interface MapRepository {
    suspend fun getPlaces(): ArrayList<Place>
    fun savePlace(place: Place)
}
