package com.kc.madridshops.activity

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.kc.madridshops.R
import com.kc.madridshops.domain.interactor.ErrorCompletion
import com.kc.madridshops.domain.interactor.SuccessCompletion
import com.kc.madridshops.domain.interactor.getallactivities.GetAllActivitiesInteractor
import com.kc.madridshops.domain.interactor.getallactivities.GetAllActivitiesInteractorImpl
import com.kc.madridshops.domain.model.Activities
import com.kc.madridshops.fragment.ActivityListFragment
import kotlinx.android.synthetic.main.activity_activity.*

class ActivityActivity : AppCompatActivity() {

    var context: Context? = null
    private var map: GoogleMap? = null
    var activityListFragment: ActivityListFragment? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_activity)
        setSupportActionBar(activity_toolbar)

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
            if (activity.latitude!=null && activity.longitude!=null){
                addPin(map!!, activity.latitude!!, activity.longitude!!, activity.name)
            }
        }
    }

    fun addPin(map: GoogleMap, latitude: Double, longitude: Double, title: String){
        map.addMarker(MarkerOptions()
                .position(LatLng(latitude,longitude))
                .title(title))
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        //Router().navigateFromMainActivitytoPicassoActivity(this)

        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
