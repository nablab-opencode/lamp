package ar.nablab.mimerendero.dtos

import com.google.android.gms.maps.model.LatLng
import io.mockk.mockk
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Test

class PlaceTest {

    @Test
    fun `Create place with full constructor`() {
        // Given
        val address = "address"
        val contact = "contact"
        val name = "name"
        val position = mockk<LatLng>(relaxed = true)
        val responsible = "responsible"
        // When
        val place = Place(address, contact, name, position, responsible)

        // Then
        Assert.assertNotNull(place)
        assertEquals(address, place.address)
        assertEquals(contact, place.contact)
        assertEquals(name, place.name)
        assertEquals(position, place.position)
        assertEquals(responsible, place.responsible)
    }
}
