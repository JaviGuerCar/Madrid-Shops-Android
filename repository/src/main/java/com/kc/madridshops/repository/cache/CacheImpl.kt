package com.kc.madridshops.repository.cache

import android.content.Context
import com.kc.madridshops.repository.DispatchOnMainThread
import com.kc.madridshops.repository.db.DBHelper
import com.kc.madridshops.repository.db.build
import com.kc.madridshops.repository.db.dao.ShopDAO
import java.lang.ref.WeakReference

internal class CacheImpl(context: Context): Cache {

    val weakContext = WeakReference<Context>(context)

    override fun deleteAllShops(success: () -> Unit, error: (errorMessage: String) -> Unit) {
        Thread(Runnable {
            // Delete in other thread
            var successDeleting = ShopDAO(cacheDBHelper()).deleteAll()
            // Main Thread
            DispatchOnMainThread(Runnable {
                if (successDeleting){
                        success()
                } else {
                    error("Error deleting")
                }
            })
        }).run()
    }

    private fun cacheDBHelper(): DBHelper {
        return build(weakContext.get()!!, "MadridShops.sqlite", 1)
    }

}