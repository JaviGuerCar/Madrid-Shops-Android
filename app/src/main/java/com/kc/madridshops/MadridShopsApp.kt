package com.kc.madridshops

import android.support.multidex.MultiDexApplication
import android.util.Log
import com.kc.madridshops.domain.interactor.ErrorCompletion
import com.kc.madridshops.domain.interactor.SuccessCompletion
import com.kc.madridshops.domain.interactor.getallactivities.GetAllActivitiesInteractorImpl
import com.kc.madridshops.domain.model.Activities


class MadridShopsApp: MultiDexApplication(){

    override fun onCreate() {
        super.onCreate()

        // init code application wide

        Log.d("App Init", "onCreate")

       val allActivitiesInteractor = GetAllActivitiesInteractorImpl(this)

        allActivitiesInteractor.execute(
                success = object: SuccessCompletion<Activities> {
                    override fun successCompletion(activities: Activities) {
                        Log.d("Activities", "Número de Activities: " + activities.count())

                        activities.activities.forEach { Log.d("ACTIVITIES", it.name) }
                    }

                },
                error = object: ErrorCompletion {
                    override fun errorCompletion(errorMessage: String) {
                        Log.d("Error", "error GetAllActivities")
                    }
                }
        )

        /*DeleteAllShopsImpl(this).execute(success = {
            Log.d("success", "success")
        },error = {
            Log.d("error", "error deleting")
        })*/

    }


    override fun onLowMemory() {
        super.onLowMemory()
    }
}