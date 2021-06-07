package ar.nablab.mimerendero.views

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.annotation.VisibleForTesting
import androidx.appcompat.app.AppCompatActivity
import ar.nablab.mimerendero.R
import ar.nablab.mimerendero.dtos.Place
import ar.nablab.mimerendero.viewModels.MapViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), OnMapReadyCallback {
    @VisibleForTesting
    lateinit var mMap: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val viewModel: MapViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_map)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.mapFragment) as SupportMapFragment
        mapFragment.getMapAsync(this)

        viewModel.getPlacesList().observe(this, {
            it.forEach { place ->
                addMarker(place)
            }
        })
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        mMap.isTrafficEnabled = false
        mMap.isIndoorEnabled = false
        mMap.uiSettings.isZoomControlsEnabled = true

        // TODO add myLocation functionality to center the map
        val cameraLocation = LatLng(CBA_LATITUDE, CBA_LONGITUDE)

        mMap.moveCamera(CameraUpdateFactory.newLatLng(cameraLocation))
        mMap.moveCamera(CameraUpdateFactory.zoomTo(DEFAULT_ZOOM))
    }

    @VisibleForTesting
     fun addMarker(place: Place) {
        mMap.addMarker(MarkerOptions().position(place.position).title(place.name))

        Log.d(TAG, place.name)
    }

    companion object {
        val TAG: String = MainActivity::class.java.simpleName
        private const val CBA_LATITUDE = -31.4165245
        private const val CBA_LONGITUDE = -64.1790721
        private const val DEFAULT_ZOOM = 13f

    }
}
