package com.kc.madridshops.domain.interactor.getallshops

import com.kc.madridshops.domain.interactor.ErrorClosure
import com.kc.madridshops.domain.interactor.ErrorCompletion
import com.kc.madridshops.domain.interactor.SuccessClosure
import com.kc.madridshops.domain.interactor.SuccessCompletion
import com.kc.madridshops.domain.model.Shop
import com.kc.madridshops.domain.model.Shops


class GetAllShopsInteractorFakeImpl: GetAllShopsInteractor {
    override fun execute(success: SuccessCompletion<Shops>, error: ErrorCompletion) {

        var allOk = true

        // connect to the repository
        if (allOk) {
            // ME creo las tiendas
            val shops = createFakeListOfShops()

            success.successCompletion(shops)
        } else {
            error.errorCompletion("Error downloading Shops")
        }
    }

    fun execute(success: SuccessClosure, error: ErrorClosure) {

        var allOk = true

        // connect to the repository
        if (allOk) {
            // ME creo las tiendas
            val shops = createFakeListOfShops()

            success(shops)
        } else {
            error("Error downloading Shops")
        }
    }

    fun createFakeListOfShops(): Shops {

        val list = ArrayList<Shop>()

        for (i in 0..100) {
            val shop = Shop(i, "Shop " + i, "Address " + i)
            list.add(shop)
        }

        val shops = Shops(list)

        return shops
    }

}