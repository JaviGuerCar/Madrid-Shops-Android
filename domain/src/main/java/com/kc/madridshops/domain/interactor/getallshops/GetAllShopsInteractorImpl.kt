package com.kc.madridshops.domain.interactor.getallshops

import android.content.Context
import android.util.Log
import com.kc.madridshops.domain.interactor.ErrorCompletion
import com.kc.madridshops.domain.interactor.SuccessCompletion
import com.kc.madridshops.domain.model.Shop
import com.kc.madridshops.domain.model.Shops
import com.kc.madridshops.repository.Repository
import com.kc.madridshops.repository.RepositoryImpl
import com.kc.madridshops.repository.model.ShopEntity
import java.lang.ref.WeakReference

class GetAllShopsInteractorImpl(context: Context) : GetAllShopsInteractor {

    private val weakContext = WeakReference<Context>(context)
    private val repository: Repository = RepositoryImpl(weakContext.get()!!)

    override fun execute(success: SuccessCompletion<Shops>, error: ErrorCompletion) {
        repository.getAllShops(success = {
            val shops: Shops = entityMapper(it)
            success.successCompletion(shops)
        }, error = {
            error(it)
        })
    }

    private fun entityMapper(list: List<ShopEntity>): Shops {
        val shopList = ArrayList<Shop>()
        list.forEach {
            //TO DO Map this propertys with a function
            val shop = mapShopEntityIntoShop(it)
            shopList.add(shop)
        }

        val shops = Shops(shopList)
        return shops
    }

    fun mapShopEntityIntoShop(shopEntity: ShopEntity): Shop {
        val shop: Shop = Shop(
            shopEntity.id.toInt(),
                shopEntity.name,
                shopEntity.description_en,
                shopEntity.description_es,
                stringWithComasToDouble(shopEntity.latitude),
                stringWithComasToDouble(shopEntity.longitude),
                shopEntity.img,
                shopEntity.logo,
                shopEntity.openingHours_es,
                shopEntity.openingHours_en,
                shopEntity.address,
                shopEntity.url
        )

        return shop
    }

    fun stringWithComasToDouble(oldValue: String): Double?{
        var newValue : Double? = null
        val stringToDouble = oldValue.replace(",","")

        try{
            newValue = stringToDouble.toDouble()
        }catch (e: Exception){
            Log.d("ERROR PARSING", "Error to convert string with comas to double: " +oldValue)
        }

        return newValue

    }
}