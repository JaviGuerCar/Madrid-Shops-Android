package com.kc.madridshops.repository.network

import com.kc.madridshops.repository.ErrorCompletion
import com.kc.madridshops.repository.SuccessCompletion

internal interface GetJsonManager {
    fun execute(url: String, success: SuccessCompletion<String>, error: ErrorCompletion)
}
