package com.kc.madridshops.domain.interactor.deleteAllActivities

import com.kc.madridshops.domain.interactor.CodeClosure
import com.kc.madridshops.domain.interactor.ErrorClosure

interface DeleteAllActivities {
    fun execute(success: CodeClosure, error: ErrorClosure)
}