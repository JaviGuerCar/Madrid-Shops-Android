package com.kc.madridshops.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import com.kc.madridshops.R
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
                .into(logoView)

        return miView
    }
}