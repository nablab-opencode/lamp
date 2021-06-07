package ar.nablab.mimerendero.repositories

import android.util.Log
import ar.nablab.mimerendero.dtos.Place
import ar.nablab.mimerendero.utils.toLatLng
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import java.util.*
import javax.inject.Inject
import kotlin.collections.HashMap
import kotlin.collections.set


class MapRepositoryImpl @Inject constructor() : MapRepository {
    private val db: FirebaseFirestore by lazy { FirebaseFirestore.getInstance() }

    override suspend fun getPlaces(): ArrayList<Place> {
        val list = arrayListOf<Place>()

        db.collection(COLLECTION_NAME)
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d(TAG, "${document.id} => ${document.data}")
                    list.add(
                        Place(
                            document.data[ADDRESS_TAG] as String,
                            document.data[CONTACT_TAG] as String,
                            document.data[NAME_TAG] as String,
                            (document.data[POSITION_TAG] as String).toLatLng(),
                            document.data[RESPONSIBLE_TAG] as String,
                        )
                    )
                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting places.", exception)
            }
            .await()

        return list
    }

    override fun savePlace(place: Place) {
        // Create a new user with a first and last name
        val data: MutableMap<String, Any> = HashMap()
        data[ADDRESS_TAG] = place.address
        data[CONTACT_TAG] = place.contact
        data[NAME_TAG] = place.name
        data[POSITION_TAG] = "${place.position.latitude},${place.position.longitude}"
        data[RESPONSIBLE_TAG] = place.responsible

        // Add a new document with a generated ID
        db.collection(COLLECTION_NAME)
            .add(data)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot added with ID: $documentReference.id")
            }
            .addOnFailureListener { e -> Log.w(TAG, "Error adding document", e) }
    }

    companion object {
        val TAG: String = MapRepository::class.java.simpleName
        private const val COLLECTION_NAME: String = "places"
        private const val ADDRESS_TAG: String = "address"
        private const val CONTACT_TAG: String = "contact"
        private const val NAME_TAG: String = "name"
        private const val POSITION_TAG: String = "position"
        private const val RESPONSIBLE_TAG: String = "responsible"
    }
}
