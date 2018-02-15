package com.kc.madridshops.domain.interactor.getallactivities

import android.content.Context
import android.util.Log
import com.kc.madridshops.domain.interactor.ErrorCompletion
import com.kc.madridshops.domain.interactor.SuccessCompletion
import com.kc.madridshops.domain.model.Activities
import com.kc.madridshops.domain.model.Activity
import com.kc.madridshops.repository.Repository
import com.kc.madridshops.repository.RepositoryImpl
import com.kc.madridshops.repository.model.ActivityEntity
import java.lang.ref.WeakReference

class GetAllActivitiesInteractorImpl(context: Context) : GetAllActivitiesInteractor {

    private val weakContext = WeakReference<Context>(context)
    private val repository: Repository = RepositoryImpl(weakContext.get()!!)

    override fun execute(success: SuccessCompletion<Activities>, error: ErrorCompletion) {
        repository.getAllActivities(success = {
            val activities: Activities = entityMapper(it)
            success.successCompletion(activities)
        }, error = {
            error(it)
        })
    }

    private fun entityMapper(list: List<ActivityEntity>): Activities {
        val activityList = ArrayList<Activity>()
        list.forEach {
            //TO DO Map this propertys with a function
            val activity = mapActivityEntityIntoShop(it)
            activityList.add(activity)
        }

        val activities = Activities(activityList)
        return activities
    }

    fun mapActivityEntityIntoShop(activityEntity: ActivityEntity): Activity {
        val activity: Activity = Activity(
                activityEntity.id.toInt(),
                activityEntity.name,
                activityEntity.description_en,
                activityEntity.description_es,
                stringWithComasToDouble(activityEntity.latitude),
                stringWithComasToDouble(activityEntity.longitude),
                activityEntity.img,
                activityEntity.logo,
                activityEntity.openingHours_es,
                activityEntity.openingHours_en,
                activityEntity.address,
                activityEntity.url
        )

        return activity
    }

    fun stringWithComasToDouble(oldValue: String): Double?{
        var newValue : Double? = null
        val stringToDouble = oldValue.replace(",","")

        try{
            newValue = stringToDouble.toDouble()
        }catch (e: Exception){
            Log.d("ERROR PARSING", "Error to convert string with comas to double")
        }

        return newValue

    }
}
