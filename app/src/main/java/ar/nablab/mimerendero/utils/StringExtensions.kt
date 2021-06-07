package ar.nablab.mimerendero.utils

import com.google.android.gms.maps.model.LatLng

fun String.toLatLng(): LatLng {
    val values = this.split(",")
    return LatLng(values.first().toDouble(), values.last().toDouble())
}
