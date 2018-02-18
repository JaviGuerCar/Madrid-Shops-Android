package com.kc.madridshops.activity

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.kc.madridshops.R
import com.kc.madridshops.adapter.MapInfoWindowAdapter
import com.kc.madridshops.domain.interactor.ErrorCompletion
import com.kc.madridshops.domain.interactor.SuccessCompletion
import com.kc.madridshops.domain.interactor.getallactivities.GetAllActivitiesInteractor
import com.kc.madridshops.domain.interactor.getallactivities.GetAllActivitiesInteractorImpl
import com.kc.madridshops.domain.model.Activities
import com.kc.madridshops.domain.model.Activity
import com.kc.madridshops.fragment.ActivityListFragment

class ActivityActivity : AppCompatActivity() {

    var context: Context? = null
    private var map: GoogleMap? = null
    var activityListFragment: ActivityListFragment? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_activity)

        Log.d("App Init", "onCreate ActivitiesActivity")

        downloadActivities()

        activityListFragment = supportFragmentManager.findFragmentById(R.id.activities_list_fragment) as ActivityListFragment

    }

    private fun downloadActivities() {

        val getAllActivitiesInteractor: GetAllActivitiesInteractor = GetAllActivitiesInteractorImpl(this)
        getAllActivitiesInteractor.execute(object: SuccessCompletion<Activities> {
            override fun successCompletion(activities: Activities) {
                Log.d("Activities", "Número de Actividades: " + activities.count())

                activityListFragment?.activitiesFromActivityActivity(activities)
                initializeMap(activities)
           }

        }, object: ErrorCompletion {
            override fun errorCompletion(errorMessage: String) {
                Toast.makeText(context, "Error downloading", Toast.LENGTH_LONG).show()
            }

        })
    }

    private fun initializeMap(activities: Activities) {
        val mapFragment = supportFragmentManager.findFragmentById(R.id.activities_map_fragment) as SupportMapFragment
        mapFragment.getMapAsync({
            Log.d("SUCCESS","HABEMUS MAPA")

            centerMapInPosition(it, 40.416775, -3.703790)
            showUserPosition(baseContext, it)
            it.uiSettings.isRotateGesturesEnabled = false
            it.uiSettings.isZoomControlsEnabled = true
            map = it

            addAllPins(activities)

            // InfoWindow Adapter
            val infoWindowAdapter = MapInfoWindowAdapter(this)
            map!!.setInfoWindowAdapter(infoWindowAdapter)

        })
    }


    fun centerMapInPosition(map: GoogleMap, latitude: Double, longitude: Double) {
        val coordinate = LatLng(latitude, longitude)
        val cameraPosition = CameraPosition.Builder().
                target(coordinate).
                zoom(13f).
                build()

        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
    }

    fun showUserPosition(context: Context, map: GoogleMap){
        if (ActivityCompat.checkSelfPermission(context, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(context, ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {

            ActivityCompat.requestPermissions(this, arrayOf(ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION), 10)

            return
        }

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 10){
            try{
                map?.isMyLocationEnabled = true
            } catch (e: SecurityException) {

            }

        }
    }

    fun addAllPins(activities: Activities){
        for (i in 0 until activities.count()){
            val activity = activities.get(i)
            if (activity.latitude != null && activity.longitude != null){
                addPin(map!!, activity)
            }
        }


    }

    fun addPin(map: GoogleMap, activity: Activity){
        map.addMarker(MarkerOptions()
                .position(LatLng(activity.latitude!!, activity.longitude!!))
                .title(activity.name)
                .snippet(activity.logo))
                .tag = activity

        // OnClick InfoWindow
        map.setOnInfoWindowClickListener{
            startActivity(ActivityDetailActivity.intent(this, it.tag as Activity))

        }

    }


}
