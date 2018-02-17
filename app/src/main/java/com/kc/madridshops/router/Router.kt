package com.kc.madridshops.router

import android.content.Intent
import com.kc.madridshops.activity.ActivityActivity
import com.kc.madridshops.activity.ShopActivity


class Router{

    fun navigateFromShopsActivitytoActivityActivity(shop: ShopActivity) {
        shop.startActivity(Intent(shop, ActivityActivity::class.java))
    }

}
