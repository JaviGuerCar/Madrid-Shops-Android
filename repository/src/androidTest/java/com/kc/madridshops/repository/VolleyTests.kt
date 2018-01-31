
package com.kc.madridshops.repository

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.kc.madridshops.repository.network.GetJsonManager
import com.kc.madridshops.repository.network.GetJsonManagerImpl

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
@RunWith(AndroidJUnit4::class)
class VolleyTests {
    // Context and DBHelper for all Tests
    val appContext = InstrumentationRegistry.getTargetContext()

    @Test
    @Throws(Exception::class)
    fun given_valid_url_we_get_non_null_json_as_string() {
        val url = "http://madrid-shops.com/json_new/getShops.php"

        val jsonManager: GetJsonManager = GetJsonManagerImpl(appContext)

        jsonManager.execute(url, success = object: SuccessCompletion<String> {
            override fun successCompletion(e: String) {
                assertTrue(e.isNotEmpty())
            }

        }, error = object : ErrorCompletion {
            override fun errorCompletion(errorMessage: String) {
                assertTrue(false)
            }

        })

    }
}
