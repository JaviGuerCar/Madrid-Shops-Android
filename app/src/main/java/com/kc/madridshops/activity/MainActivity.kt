package com.kc.madridshops.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageButton
import com.kc.madridshops.R
import com.kc.madridshops.router.Router

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ShopsButton = findViewById<ImageButton>(R.id.shopsButton)
        val ActivitiesButton = findViewById<ImageButton>(R.id.activities_button)

        ShopsButton.setOnClickListener {
            Router().navigateFromMainActivitytoShopActivity(this)
        }

        ActivitiesButton.setOnClickListener {
            Router().navigateFromMainActivitytoActivityActivity(this)
        }
    }
}
