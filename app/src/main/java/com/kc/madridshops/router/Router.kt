package com.kc.madridshops.router

import android.content.Intent
import com.kc.madridshops.activity.ActivityActivity
import com.kc.madridshops.activity.MainActivity
import com.kc.madridshops.activity.ShopActivity


class Router{

    fun navigateFromMainActivitytoActivityActivity(main: MainActivity) {
        main.startActivity(Intent(main, ActivityActivity::class.java))
    }

    fun navigateFromMainActivitytoShopActivity(main: MainActivity) {
        main.startActivity(Intent(main, ShopActivity::class.java))
    }

}
