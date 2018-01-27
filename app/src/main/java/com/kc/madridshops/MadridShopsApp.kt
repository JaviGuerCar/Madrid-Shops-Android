package com.kc.madridshops

import android.support.multidex.MultiDexApplication
import android.util.Log
import madridshops.kc.com.domain.interactor.ErrorCompletion
import madridshops.kc.com.domain.interactor.getallshops.GetAllShopsInteractorFakeImpl
import madridshops.kc.com.domain.interactor.SuccessCompletion
import madridshops.kc.com.domain.model.Shops


class MadridShopsApp: MultiDexApplication(){

    override fun onCreate() {
        super.onCreate()

        // init code application wide

        Log.d("App Init", "onCreate")

        val allShopsInteractor = GetAllShopsInteractorFakeImpl()

        allShopsInteractor.execute(success = { shops: Shops -> Unit

        }, error = { msg: String -> Unit

        })

        allShopsInteractor.execute(
                success = object: SuccessCompletion<Shops>{
                    override fun successCompletion(shops: Shops) {
                        Log.d("Shops", "Número de Tiendas: " + shops.count())
                    }

                },
                error = object: ErrorCompletion{
                    override fun errorCompletion(errorMessage: String) {

                    }

                }
        )
    }

    override fun onLowMemory() {
        super.onLowMemory()
    }
}