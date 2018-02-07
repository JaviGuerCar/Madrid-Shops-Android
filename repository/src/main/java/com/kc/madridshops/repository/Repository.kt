package com.kc.madridshops.repository

import com.kc.madridshops.repository.model.ShopEntity

interface Repository {
    fun getAllShops(success: (shops: List<ShopEntity>) -> Unit, error: (errorMessage: String) -> Unit)
    fun deleteAllShops(success: () -> Unit, error: (errorMessage: String) -> Unit)
}