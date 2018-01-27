package madridshops.kc.com.domain.interactor.getallshops

import madridshops.kc.com.domain.interactor.ErrorClosure
import madridshops.kc.com.domain.interactor.ErrorCompletion
import madridshops.kc.com.domain.interactor.SuccessClosure
import madridshops.kc.com.domain.interactor.SuccessCompletion
import madridshops.kc.com.domain.model.Shop
import madridshops.kc.com.domain.model.Shops


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