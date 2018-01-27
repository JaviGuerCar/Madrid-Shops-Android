package madridshops.kc.com.domain.interactor

import madridshops.kc.com.domain.model.Shops

typealias SuccessClosure = (shops: Shops) -> Unit
typealias ErrorClosure = (msg: String) -> Unit
typealias CodeClosure = () -> Unit
