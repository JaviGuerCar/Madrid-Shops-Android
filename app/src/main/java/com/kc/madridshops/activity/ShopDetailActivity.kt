package com.kc.madridshops.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import com.kc.madridshops.R
import com.kc.madridshops.domain.model.Shop
import com.squareup.picasso.Picasso

class ShopDetailActivity : AppCompatActivity() {

    companion object {
        private val EXTRA_SHOP = "EXTRA_SHOP"
        fun intent(context: Context, shop: Shop): Intent {
            val intent = Intent(context, ShopDetailActivity::class.java)
            intent.putExtra(EXTRA_SHOP, shop)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val shop = intent.getSerializableExtra(EXTRA_SHOP) as Shop

        // Synchronize Model-View
        val shopName = findViewById<TextView>(R.id.detail_shop_name_textView)
        val image = findViewById<ImageView>(R.id.detail_shop_image_view)
        val shopDescription = findViewById<TextView>(R.id.detail_shop_description_textView)
        val shopURL = findViewById<TextView>(R.id.detail_shop_url_textView)
        val shopMapImage = findViewById<ImageView>(R.id.detail_shop_map_image_view)
        val shopHours = findViewById<TextView>(R.id.detail_shop_opening_hours_textView)
        val shopAddress = findViewById<TextView>(R.id.detail_shop_address_textView)

        shopName.text = shop.name
        shopDescription.text = shop.description_es
        shopURL.text = shop.url
        shopHours.text = shop.openingHours_es
        shopAddress.text = shop.address

        val shopURLMapStatic = "https://maps.googleapis.com/maps/api/staticmap?center=${shop.latitude},${shop.longitude}&zoom=17&size=320x220&markers=color:green%7C${shop.latitude},${shop.longitude}"

        // Load Image Background
        Picasso.with(this)
                .load(shop.img)
                .placeholder(R.drawable.noimagelarge)
                .into(image)

        // Load Image Static Map
        Picasso.with(this)
                .load(shopURLMapStatic)
                .placeholder(R.drawable.noimagelarge)
                .into(shopMapImage)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            // Sabemos que se ha pulsado la flecha de Back
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
