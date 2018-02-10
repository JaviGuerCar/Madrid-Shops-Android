package com.kc.madridshops.router

import android.content.Intent
import com.kc.madridshops.activity.PicassoActivity
import com.kc.madridshops.activity.ShopActivity


class Router{

    fun navigateFromMainActivitytoPicassoActivity(shop: ShopActivity) {
        shop.startActivity(Intent(shop, PicassoActivity::class.java))
    }
}
