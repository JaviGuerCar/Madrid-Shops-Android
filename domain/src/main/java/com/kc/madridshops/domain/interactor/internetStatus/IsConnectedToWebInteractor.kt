package com.kc.madridshops.domain.interactor.internetStatus

import android.content.Context
import com.kc.madridshops.domain.interactor.CodeClosure
import com.kc.madridshops.domain.interactor.ErrorClosure

interface IsConnectedToWebInteractor {
    fun execute(context: Context, success: CodeClosure, error: ErrorClosure)
}