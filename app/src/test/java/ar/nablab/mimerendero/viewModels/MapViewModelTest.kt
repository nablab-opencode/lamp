package ar.nablab.mimerendero.viewModels

import androidx.lifecycle.MutableLiveData
import ar.nablab.mimerendero.dtos.Place
import ar.nablab.mimerendero.repositories.MapRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class MapViewModelTest {
    private val repository = mockk<MapRepository>(relaxed = true)
    private val mapViewModel = MapViewModel(repository)

    @Test
    fun getPlacesList() {
        val list = arrayListOf<Place>(mockk())
        val liveData = MutableLiveData<ArrayList<Place>>()
        liveData.value = list
        // Given
        every { runBlocking { repository.getPlaces() } } returns list
        // When
        val result = mapViewModel.getPlacesList()
        // Then
        assertEquals(liveData.value, result.value)
    }

    @Test
    fun savePlace() {
        // Given
        val place = mockk<Place>()

        // When
        mapViewModel.savePlace(place)

        // Then
        verify {
            repository.savePlace(place)
        }
    }
}
