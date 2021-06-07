package ar.nablab.mimerendero.views

import ar.nablab.mimerendero.dtos.Place
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.spyk
import io.mockk.verify
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class MainActivityTest {
    var mapMock = mockk<GoogleMap>(relaxed = true)
    var mainActivity = spyk(MainActivity())

    @Test
    fun onMapReady() {
        // Given
        mockkStatic(CameraUpdateFactory::class)
        every { CameraUpdateFactory.newLatLng(any()) } returns mockk()
        every { CameraUpdateFactory.zoomTo(any()) } returns mockk()
        // When
        mainActivity.onMapReady(mapMock)

        // Then
    }

    @Test
    fun `addMarker to map`() {
        // Given
        mainActivity.mMap = mapMock
        val placeMock = mockk<Place>(relaxed = true)

        // When
        mainActivity.addMarker(placeMock)

        // Then
        verify { mapMock.addMarker(any()) }
    }
}
