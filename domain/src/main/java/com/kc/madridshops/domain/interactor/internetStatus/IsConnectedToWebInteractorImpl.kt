package com.kc.madridshops.domain.interactor.internetStatus

import com.kc.madridshops.domain.interactor.CodeClosure
import com.kc.madridshops.domain.interactor.ErrorClosure

class IsConnectedToWebInteractorImpl : IsConnectedToWebInteractor {
    override fun execute(success: CodeClosure, error: ErrorClosure) {
        success()
    }
}