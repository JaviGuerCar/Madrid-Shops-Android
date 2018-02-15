package com.kc.madridshops.repository

import android.content.Context
import com.fasterxml.jackson.databind.exc.InvalidFormatException
import com.kc.madridshops.repository.cache.Cache
import com.kc.madridshops.repository.cache.CacheImpl
import com.kc.madridshops.repository.model.ActivitiesResponseEntity
import com.kc.madridshops.repository.model.ActivityEntity
import com.kc.madridshops.repository.model.ShopEntity
import com.kc.madridshops.repository.model.ShopsResponseEntity
import com.kc.madridshops.repository.network.GetJsonManager
import com.kc.madridshops.repository.network.GetJsonManagerImpl
import com.kc.madridshops.repository.network.json.JsonEntitiesParser
import madridshops.kc.com.repository.BuildConfig
import java.lang.ref.WeakReference

class RepositoryImpl(context:Context): Repository {

    private val weakContext = WeakReference<Context>(context)
    private val cache: Cache = CacheImpl(weakContext.get()!!)

    override fun getAllShops(success: (shops: List<ShopEntity>) -> Unit, error: (errorMessage: String) -> Unit) {
        // read all shops from cache
        cache.getAllShops(
            success = {
                // if there´s ships in cache --> return them
                success(it)

            }, error = {
                // if no shop in cache --> download from web
                populateCache(success, error)
            })

    }

    private fun populateCache(success: (shops: List<ShopEntity>) -> Unit, error: (errorMessage: String) -> Unit) {
        // perform network request

        val jsonManager: GetJsonManager = GetJsonManagerImpl(weakContext.get()!!)
        jsonManager.execute(BuildConfig.MADRID_SHOPS_SERVER_URL, success = object: SuccessCompletion<String>{
            override fun successCompletion(e: String) {
                // If all good, parse downloaded data
                val parser = JsonEntitiesParser()
                var responseEntity: ShopsResponseEntity

                try {
                    responseEntity = parser.parse<ShopsResponseEntity>(e)
                }catch (e: InvalidFormatException){
                    error("ERROR PARSING")
                    return
                }
                // store result in cache
                cache.saveAllShops(responseEntity.result, success = {
                    success(responseEntity.result)
                }, error = {
                    error("Something happened when saveAllShops")
                })

            }

        }, error = object: ErrorCompletion{
            override fun errorCompletion(errorMessage: String) {

            }
        })

    }

    override fun deleteAllShops(success: () -> Unit, error: (errorMessage: String) -> Unit) {
        cache.deleteAllShops(success, error)
    }


    override fun getAllActivities(success: (activities: List<ActivityEntity>) -> Unit, error: (errorMessage: String) -> Unit) {
        // read all shops from cache
        cache.getAllActivities(
                success = {
                    // if there´s activities in cache --> return them
                    success(it)

                }, error = {
            // if no activities in cache --> download from web
            populateActivitiesCache(success, error)
        })
    }


    private fun populateActivitiesCache(success: (activities: List<ActivityEntity>) -> Unit, error: (errorMessage: String) -> Unit) {
        // perform network request

        val jsonManager: GetJsonManager = GetJsonManagerImpl(weakContext.get()!!)
        jsonManager.execute(BuildConfig.MADRID_ACTIVITIES_SERVER_URL, success = object: SuccessCompletion<String>{
            override fun successCompletion(e: String) {
                // If all good, parse downloaded data
                val parser = JsonEntitiesParser()
                var responseEntity: ActivitiesResponseEntity

                try {
                    responseEntity = parser.parse<ActivitiesResponseEntity>(e)
                }catch (e: InvalidFormatException){
                    error("ERROR PARSING")
                    return
                }
                // store result in cache
                cache.saveAllActivities(responseEntity.result, success = {
                    success(responseEntity.result)
                }, error = {
                    error("Something happened when saveAllActivities")
                })

            }

        }, error = object: ErrorCompletion{
            override fun errorCompletion(errorMessage: String) {

            }
        })

    }

    override fun deleteAllActivities(success: () -> Unit, error: (errorMessage: String) -> Unit) {

    }

}