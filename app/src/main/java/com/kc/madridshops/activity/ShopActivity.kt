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
import com.kc.madridshops.domain.interactor.getallshops.GetAllShopsInteractor
import com.kc.madridshops.domain.interactor.getallshops.GetAllShopsInteractorImpl
import com.kc.madridshops.domain.model.Shop
import com.kc.madridshops.domain.model.Shops
import com.kc.madridshops.fragment.ShopListFragment
import kotlinx.android.synthetic.main.activity_shop.*

class ShopActivity : AppCompatActivity() {

    var context: Context? = null
    private var map: GoogleMap? = null
    var shopListFragment: ShopListFragment? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop)
        setSupportActionBar(toolbar)

        Log.d("App Init", "onCreate ShopActivity")

        downloadShops()

        shopListFragment = supportFragmentManager.findFragmentById(R.id.shops_list_fragment) as ShopListFragment

    }

    private fun downloadShops() {

        val getAllShopsInteractor: GetAllShopsInteractor = GetAllShopsInteractorImpl(this)
        getAllShopsInteractor.execute(object: SuccessCompletion<Shops> {
            override fun successCompletion(shops: Shops) {
                Log.d("Shops", "Número de Tiendas: " + shops.count())

                shopListFragment?.shopsFromShopActivity(shops)
                initializeMap(shops)
           }

        }, object: ErrorCompletion {
            override fun errorCompletion(errorMessage: String) {
                Toast.makeText(context, "Error downloading", Toast.LENGTH_LONG).show()
            }

        })
    }

    private fun initializeMap(shops: Shops) {
        val mapFragment = supportFragmentManager.findFragmentById(R.id.shops_map_fragment) as SupportMapFragment
        mapFragment.getMapAsync({
            Log.d("SUCCESS","HABEMUS MAPA")

            centerMapInPosition(it, 40.416775, -3.703790)
            showUserPosition(baseContext, it)
            it.uiSettings.isRotateGesturesEnabled = false
            it.uiSettings.isZoomControlsEnabled = true
            map = it

            addAllPins(shops)

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

    fun addAllPins(shops: Shops){
        for (i in 0 until shops.count()){
            val shop = shops.get(i)
            if (shop.latitude!=null && shop.longitude!=null){
                addPin(map!!, shop)
            }
        }
    }

    fun addPin(map: GoogleMap, shop: Shop) {
        map.addMarker(MarkerOptions()
                .position(LatLng(shop.latitude!!, shop.longitude!!))
                .title(shop.name)
                .snippet(shop.logo))
                .tag = shop

        // OnClick InfoWindow
        map.setOnInfoWindowClickListener {
            startActivity(ShopDetailActivity.intent(this, it.tag as Shop))
        }
    }
}
