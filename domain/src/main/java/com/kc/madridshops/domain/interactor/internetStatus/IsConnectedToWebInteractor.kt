package com.kc.madridshops.domain.interactor.internetStatus

import com.kc.madridshops.domain.interactor.CodeClosure
import com.kc.madridshops.domain.interactor.ErrorClosure

interface IsConnectedToWebInteractor {
    fun execute(success: CodeClosure, error: ErrorClosure)
}