package com.kc.madridshops.domain.model

import java.io.Serializable

data class Activity(
    val id: Int,
    val name: String,
    val description_en: String,
    val description_es: String,
    val latitude: Double?,
    val longitude: Double?,
    val img: String,
    val logo: String,
    val openingHours_es: String,
    val openingHours_en: String,
    val address: String,
    val url: String): Serializable {

    init {
        Activities(ArrayList<Activity>())
    }

}

class Activities(val activities: MutableList<Activity>): Aggregate<Activity> {
    override fun count(): Int {
        return activities.size
    }

    override fun all(): List<Activity> {
        return activities
    }

    override fun get(position: Int): Activity {
        return activities.get(position)
    }

    override fun add(element: Activity) {
        activities.add(element)
    }

    override fun delete(position: Int) {
        activities.removeAt(position)
    }

    override fun delete(element: Activity) {
        activities.remove(element)
    }

}