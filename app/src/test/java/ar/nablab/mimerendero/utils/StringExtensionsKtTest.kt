package ar.nablab.mimerendero.utils

import org.junit.Assert.assertEquals
import org.junit.Test


class StringExtensionsKtTest {

    @Test
    fun `toLang correct data`() {
        // Given
        val latitude = -64.33333
        val longitude = -33.32321
        val coordinate = "$latitude,$longitude"
        // When
        val result = coordinate.toLatLng()
        // Then
        assertEquals(latitude, result.latitude, 0.0)
        assertEquals(longitude, result.longitude, 0.0)
    }
}
