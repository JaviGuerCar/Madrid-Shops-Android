package madridshops.kc.com.domain.interactor.internetStatus

import madridshops.kc.com.domain.interactor.CodeClosure
import madridshops.kc.com.domain.interactor.ErrorClosure

interface IsConnectedToWebInteractor {
    fun execute(success: CodeClosure, error: ErrorClosure)
}