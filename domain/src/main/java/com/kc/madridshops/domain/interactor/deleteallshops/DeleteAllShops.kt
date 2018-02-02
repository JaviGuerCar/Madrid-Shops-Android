package com.kc.madridshops.domain.interactor.deleteallshops

import com.kc.madridshops.domain.interactor.CodeClosure
import com.kc.madridshops.domain.interactor.ErrorClosure

interface DeleteAllShops {
    fun execute(success: CodeClosure, error: ErrorClosure)
}