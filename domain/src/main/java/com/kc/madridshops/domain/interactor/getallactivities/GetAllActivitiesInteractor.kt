package com.kc.madridshops.domain.interactor.getallactivities

import com.kc.madridshops.domain.interactor.ErrorCompletion
import com.kc.madridshops.domain.interactor.SuccessCompletion
import com.kc.madridshops.domain.model.Activities

interface GetAllActivitiesInteractor {
    fun execute(success: SuccessCompletion<Activities>, error: ErrorCompletion)
}