package ar.nablab.mimerendero.dtos

import com.google.android.gms.maps.model.LatLng

data class Place(
    val address: String,
    val contact: String,
    val name: String,
    val position: LatLng,
    val responsible: String
)
