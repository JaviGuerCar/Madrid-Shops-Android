package com.kc.madridshops

import android.support.multidex.MultiDexApplication
import android.util.Log
import com.kc.madridshops.domain.interactor.ErrorCompletion
import com.kc.madridshops.domain.interactor.SuccessCompletion
import com.kc.madridshops.domain.interactor.getallshops.GetAllShopsInteractorFakeImpl
import com.kc.madridshops.domain.model.Shops
import com.kc.madridshops.repository.db.build
import com.kc.madridshops.repository.db.dao.ShopDAO
import com.kc.madridshops.repository.model.ShopEntity


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

        test()
    }

    private fun test() {
        // NEVER DO THIS!!!
        // HORROR !!!

        val dbhelper = build(this, "mydb.sqlite", 1)

        val shopEntityDao = ShopDAO(dbhelper)


        val deletedAll = shopEntityDao.deleteAll()

        val shop = ShopEntity(3,3,"My shop 1", "desc 1"
                , 1.0f, 2.0f,"","", "", "")


        val shop2 = ShopEntity(4,4,"My shop 2", "desc 2"
                , 1.0f, 2.0f,"","", "", "")


        val id = shopEntityDao.insert(shop)
        val id2 = shopEntityDao.insert(shop2)

        shopEntityDao.query().forEach {
            Log.d("Shop", it.name + " - " + it.description_en)

        }

    }

    override fun onLowMemory() {
        super.onLowMemory()
    }
}