package com.kc.madridshops.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.kc.madridshops.R
import com.kc.madridshops.domain.model.Activities
import com.kc.madridshops.domain.model.Activity
import com.squareup.picasso.Picasso


class ActivityRecyclerViewAdapter(val activities: Activities?): RecyclerView.Adapter<ActivityRecyclerViewAdapter.ViewHolder>(){

    var onClickListener: View.OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val activityItemView = LayoutInflater.from(parent?.context).inflate(R.layout.item, parent, false)

        activityItemView.setOnClickListener(onClickListener)
        return ViewHolder(activityItemView)
    }

    override fun getItemCount(): Int {
        if (activities != null){
            return activities.count()
        }
        return 0
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        if (activities != null){
            holder?.bindActivities(activities.get(position))
        }

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val context = itemView.context
        val activityName = itemView.findViewById<TextView>(R.id.name_text_view)
        val logo = itemView.findViewById<ImageView>(R.id.logo_image_view)
        val image = itemView.findViewById<ImageView>(R.id.background_image_view)


        fun bindActivities(activity: Activity){
            // Synchronize Model-View
            activityName.text = activity.name

            //Picasso.with(context).setIndicatorsEnabled(true)
            //Picasso.with(context).isLoggingEnabled

            // Load Logo
            Picasso.with(context)
                    .load(activity.logo)
                    .placeholder(R.drawable.noimage)
                    .into(logo)

            // Load Image Background
            Picasso.with(context)
                    .load(activity.img)
                    .placeholder(R.drawable.noimagelarge)
                    .into(image)
        }
    }

}