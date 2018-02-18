package com.kc.madridshops.domain.interactor.internetStatus

import android.content.Context
import android.net.ConnectivityManager
import com.kc.madridshops.domain.interactor.CodeClosure
import com.kc.madridshops.domain.interactor.ErrorClosure

class IsConnectedToWebInteractorImpl : IsConnectedToWebInteractor {
    override fun execute(context: Context, success: CodeClosure, error: ErrorClosure) {
        val conManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager

        val activeNetwork = conManager?.activeNetworkInfo

        if (activeNetwork != null && activeNetwork.isConnectedOrConnecting) {
            success()
        } else {
            error("Error connecting to web")
        }

    }
}