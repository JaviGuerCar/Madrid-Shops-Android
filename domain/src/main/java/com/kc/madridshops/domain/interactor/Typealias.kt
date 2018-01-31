package com.kc.madridshops.domain.interactor

import com.kc.madridshops.domain.model.Shops

typealias SuccessClosure = (shops: Shops) -> Unit
typealias ErrorClosure = (msg: String) -> Unit
typealias CodeClosure = () -> Unit
