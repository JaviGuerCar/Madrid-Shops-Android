package com.kc.madridshops.repository.cache

import com.kc.madridshops.repository.model.ActivityEntity
import com.kc.madridshops.repository.model.ShopEntity

internal interface Cache {
    fun deleteAllShops(success: () -> Unit, error: (errorMessage: String) -> Unit)
    fun getAllShops(success: (shops: List<ShopEntity>) -> Unit, error: (errorMessage: String) -> Unit)
    fun saveAllShops(shops: List<ShopEntity>, success: () -> Unit, error: (errorMessage: String) -> Unit)

    // Activity
    fun deleteAllActivities(success: () -> Unit, error: (errorMessage: String) -> Unit)
    fun getAllActivities(success: (shops: List<ActivityEntity>) -> Unit, error: (errorMessage: String) -> Unit)
    fun saveAllActivities(shops: List<ActivityEntity>, success: () -> Unit, error: (errorMessage: String) -> Unit)

}