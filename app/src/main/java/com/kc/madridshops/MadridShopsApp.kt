package com.kc.madridshops

import android.support.multidex.MultiDexApplication
import android.util.Log


class MadridShopsApp: MultiDexApplication(){

    override fun onCreate() {
        super.onCreate()

        // init code application wide

        Log.d("App Init", "onCreate")

       /* val allShopsInteractor = GetAllShopsInteractorImpl(this)

        allShopsInteractor.execute(
                success = object: SuccessCompletion<Shops> {
                    override fun successCompletion(shops: Shops) {
                        Log.d("Shops", "Número de Tiendas: " + shops.count())

                        shops.shops.forEach { Log.d("Shop", it.name) }
                    }

                },
                error = object: ErrorCompletion {
                    override fun errorCompletion(errorMessage: String) {
                        Log.d("Error", "error GetAllShops")
                    }
                }
        )*/

        /*DeleteAllShopsImpl(this).execute(success = {
            Log.d("success", "success")
        },error = {
            Log.d("error", "error deleting")
        })*/

    }


    override fun onLowMemory() {
        super.onLowMemory()
    }
}