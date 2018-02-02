package com.kc.madridshops

import android.support.multidex.MultiDexApplication
import android.util.Log
import com.kc.madridshops.domain.interactor.ErrorCompletion
import com.kc.madridshops.domain.interactor.SuccessCompletion
import com.kc.madridshops.domain.interactor.deleteallshops.DeleteAllShopsImpl
import com.kc.madridshops.domain.interactor.getallshops.GetAllShopsInteractorFakeImpl
import com.kc.madridshops.domain.model.Shops


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
                success = object: SuccessCompletion<Shops> {
                    override fun successCompletion(shops: Shops) {
                        Log.d("Shops", "Número de Tiendas: " + shops.count())
                    }

                },
                error = object: ErrorCompletion {
                    override fun errorCompletion(errorMessage: String) {

                    }

                }
        )

        DeleteAllShopsImpl(this).execute(success = {
            Log.d("success", "success")
        },error = {
            Log.d("error", "error deleting")
        })

    }


    override fun onLowMemory() {
        super.onLowMemory()
    }
}