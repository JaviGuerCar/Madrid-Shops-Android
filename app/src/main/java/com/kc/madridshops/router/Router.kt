package com.kc.madridshops.router

import android.content.Intent
import com.kc.madridshops.activity.MainActivity
import com.kc.madridshops.activity.PicassoActivity


class Router{

    fun navigateFromMainActivitytoPicassoActivity(main: MainActivity) {
        main.startActivity(Intent(main, PicassoActivity::class.java))
    }
}
