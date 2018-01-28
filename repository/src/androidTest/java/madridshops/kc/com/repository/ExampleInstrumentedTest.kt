
package madridshops.kc.com.repository

import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import madridshops.kc.com.repository.db.build
import madridshops.kc.com.repository.db.dao.ShopDAO
import madridshops.kc.com.repository.model.ShopEntity

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    // Context and DBHelper for all Tests
    val appContext = InstrumentationRegistry.getTargetContext()
    val dbHelper = build(appContext,"mydb.sqlite",1)

    @Test
    @Throws(Exception::class)
    fun given_valid_shopEntity_it_gets_inserted_correctly() {

        val shop = ShopEntity(1,1,"My shop","",1.0f,2.0f,"","","","")

        val shopEntityDao = ShopDAO(dbHelper)

        val id = shopEntityDao.insert(shop)

        assertTrue(id > 0)

    }
}
