
package com.kc.madridshops.repository

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.util.Log
import com.kc.madridshops.repository.db.build
import com.kc.madridshops.repository.db.dao.ShopDAO
import com.kc.madridshops.repository.model.ShopEntity
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
@RunWith(AndroidJUnit4::class)
class ShopDAOTests {
    // Context and DBHelper for all Tests
    val appContext = InstrumentationRegistry.getTargetContext()
    val dbHelper = build(appContext, "mydb.sqlite", 1)

    @Test
    @Throws(Exception::class)
    fun given_valid_shopEntity_it_gets_inserted_correctly() {

        val shop = ShopEntity(1,1,"My shop","",1.0f,2.0f,"","","","")

        val shopEntityDao = ShopDAO(dbHelper)

        val id = shopEntityDao.insert(shop)

        assertTrue(id > 0)

    }


    private fun test() {

        val dbhelper = build(appContext, "mydb.sqlite", 1)

        val shopEntityDao = ShopDAO(dbhelper)


        val deletedAll = shopEntityDao.deleteAll()

        val shop = ShopEntity(3,3,"My shop 1", "desc 1"
                , 1.0f, 2.0f,"","", "", "")


        val shop2 = ShopEntity(4,4,"My shop 2", "desc 2"
                , 1.0f, 2.0f,"","", "", "")


        val id = shopEntityDao.insert(shop)
        val id2 = shopEntityDao.insert(shop2)

        shopEntityDao.query().forEach {
            Log.d("Shop", it.name + " - " + it.description)

        }

    }
}
