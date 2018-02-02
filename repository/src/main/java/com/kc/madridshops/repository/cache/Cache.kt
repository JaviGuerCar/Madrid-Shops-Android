package com.kc.madridshops.repository.cache

internal interface Cache {
    fun deleteAllShops(success: () -> Unit, error: (errorMessage: String) -> Unit)
}