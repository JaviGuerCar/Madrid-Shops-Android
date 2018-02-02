package com.kc.madridshops.repository

import android.content.Context
import com.kc.madridshops.repository.cache.Cache
import com.kc.madridshops.repository.cache.CacheImpl
import java.lang.ref.WeakReference

class RepositoryImpl(context:Context): Repository {

    val weakContext = WeakReference<Context>(context)
    private val cache: Cache = CacheImpl(weakContext.get()!!)

    override fun deleteAllShops(success: () -> Unit, error: (errorMessage: String) -> Unit) {
        cache.deleteAllShops(success, error)
    }

}