package madridshops.kc.com.domain.interactor.getallshops

import madridshops.kc.com.domain.interactor.ErrorCompletion
import madridshops.kc.com.domain.interactor.SuccessCompletion
import madridshops.kc.com.domain.model.Shops

interface GetAllShopsInteractor {
    fun execute(success: SuccessCompletion<Shops>, error: ErrorCompletion)
}