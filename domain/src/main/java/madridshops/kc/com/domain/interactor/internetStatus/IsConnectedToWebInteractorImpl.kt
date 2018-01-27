package madridshops.kc.com.domain.interactor.internetStatus

import madridshops.kc.com.domain.interactor.CodeClosure
import madridshops.kc.com.domain.interactor.ErrorClosure

class IsConnectedToWebInteractorImpl : IsConnectedToWebInteractor {
    override fun execute(success: CodeClosure, error: ErrorClosure) {
        success()
    }
}