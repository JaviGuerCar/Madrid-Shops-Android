package com.kc.madridshops.repository.cache

import android.content.Context
import com.kc.madridshops.repository.DispatchOnMainThread
import com.kc.madridshops.repository.db.DBHelper
import com.kc.madridshops.repository.db.build
import com.kc.madridshops.repository.db.dao.ShopDAO
import com.kc.madridshops.repository.model.ShopEntity
import java.lang.ref.WeakReference

internal class CacheImpl(context: Context): Cache {

    val weakContext = WeakReference<Context>(context)


    override fun getAllShops(success: (shops: List<ShopEntity>) -> Unit, error: (errorMessage: String) -> Unit) {
        Thread(Runnable {
            // GetAll in other thread
            var shops = ShopDAO(cacheDBHelper()).query()
            // Main Thread
            DispatchOnMainThread(Runnable {
                if (shops.count() > 0){
                    success(shops)
                } else {
                    error("No shops in Cache")
                }
            })
        }).run()

    }

    override fun saveAllShops(shops: List<ShopEntity>, success: () -> Unit, error: (errorMessage: String) -> Unit) {
        Thread(Runnable {
            try {
                shops.forEach{ ShopDAO(cacheDBHelper()).insert(it)}

                DispatchOnMainThread(Runnable {
                    success()
                })

            } catch (e: Exception){
                DispatchOnMainThread(Runnable {
                    error("Error saving shops in cache")
                })
            }

        }).run()
    }


    override fun deleteAllShops(success: () -> Unit, error: (errorMessage: String) -> Unit) {
        Thread(Runnable {
            // Delete in other thread
            var successDeleting = ShopDAO(cacheDBHelper()).deleteAll()
            // Main Thread
            DispatchOnMainThread(Runnable {
                if (successDeleting){
                        success()
                } else {
                    error("Error deleting shops in cache")
                }
            })
        }).run()
    }

    private fun cacheDBHelper(): DBHelper {
        return build(weakContext.get()!!, "MadridShops.sqlite", 1)
    }

}