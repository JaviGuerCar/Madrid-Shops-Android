package com.kc.madridshops.repository

import android.os.Handler
import android.os.Looper


// (Looper)Cola Ejecución - Vuelve al Hilo Principal
fun DispatchOnMainThread(codeToRun: Runnable) {
    val uiHandler: Handler = Handler(Looper.getMainLooper())
    uiHandler.post(codeToRun)
}
