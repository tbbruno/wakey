package br.cin.ufpe.wakey

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_bottom_sheet.*

class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Config map fragment
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.mapView) as SupportMapFragment
        mapFragment.getMapAsync(this)

        // Config wakeys list onClick method
        listview.setOnItemClickListener { _, _, position, _ ->
            val detailIntent = WakeyDetailActivity.newIntent(this)
            startActivity(detailIntent)
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Move the camera to a certain location and zoom in
        val location = LatLng(-8.0557, -34.9516)
        val newPosition = CameraPosition.Builder()
            .target(location)
            .zoom(15f)
            .build()

        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(newPosition))
    }
}
