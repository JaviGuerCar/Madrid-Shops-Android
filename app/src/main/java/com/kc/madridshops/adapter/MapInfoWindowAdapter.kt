package com.kc.madridshops.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import com.kc.madridshops.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso


class MapInfoWindowAdapter(context: Context) : GoogleMap.InfoWindowAdapter {
    private val context: Context

    init {
        this.context = context.getApplicationContext()
    }

    override fun getInfoWindow(marker: Marker): View? {
        return null
    }

    override fun getInfoContents(marker: Marker): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val miView = inflater.inflate(R.layout.map_info_window, null)

        val nameText = miView.findViewById<TextView>(R.id.info_window_name_text_view)
        val logoView = miView.findViewById<ImageView>(R.id.info_window_image_view)
        val logo = marker.snippet

        nameText.text = marker.title

        // Load Logo
        Picasso.with(context)
                .load(logo)
                .placeholder(R.drawable.noimage)
                .into(logoView, MarkerCallBack(marker, logo, logoView, context))

        return miView
    }
}

class MarkerCallBack(val marker: Marker, val URL: String, val logo: ImageView, val context: Context): Callback {
    override fun onSuccess() {
        if (marker != null && marker.isInfoWindowShown()){
            marker.hideInfoWindow()

            Picasso.with(context)
                    .load(URL)
                    .into(logo)

            marker.showInfoWindow()
        }
    }

    override fun onError() {
        Log.d("Picasso error","Error loading Marker Logo")
    }

}