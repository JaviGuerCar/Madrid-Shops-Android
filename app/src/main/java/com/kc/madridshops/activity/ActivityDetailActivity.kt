package com.kc.madridshops.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import com.kc.madridshops.R
import com.kc.madridshops.domain.model.Activity
import com.squareup.picasso.Picasso

class ActivityDetailActivity : AppCompatActivity() {

    companion object {
        private val EXTRA_ACTIVITY = "EXTRA_ACTIVITY"

        fun intent(context: Context, activity: Activity): Intent {
            val intent = Intent(context, ActivityDetailActivity::class.java)
            intent.putExtra(EXTRA_ACTIVITY, activity)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val activity = intent.getSerializableExtra(EXTRA_ACTIVITY) as Activity

        // Synchronize Model-View
        val activityName = findViewById<TextView>(R.id.detail_activity_name_textView)
        val activityImage = findViewById<ImageView>(R.id.detail_activity_image_view)
        val activityDescription = findViewById<TextView>(R.id.detail_activity_description_textView)
        val activityURL = findViewById<TextView>(R.id.detail_activity_url_textView)
        val activityHours = findViewById<TextView>(R.id.detail_activity_opening_hours_textView)
        val activityAddress = findViewById<TextView>(R.id.detail_activity_address_textView)

        activityName.text = activity.name
        activityDescription.text = activity.description_es
        activityURL.text = activity.url
        activityHours.text = activity.openingHours_es
        activityAddress.text = activity.address

        // Load Image Background
        Picasso.with(this)
                .load(activity.img)
                .placeholder(R.drawable.noimagelarge)
                .into(activityImage)

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item?.itemId == android.R.id.home){
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
