package com.kc.madridshops

import com.kc.madridshops.repository.model.ShopEntity
import com.kc.madridshops.repository.network.json.JsonEntitiesParser
import com.kc.madridshops.util.ReadJsonFile
import junit.framework.Assert.*
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class JSONParsingTests {
    @Test
    @Throws(Exception::class)
    fun given_valid_string_containing_json_it_parses_correctly() {

        val shopsJson = ReadJsonFile().loadJSONFromAsset("shop.json")

        assertTrue(!shopsJson.isEmpty())

        // test parsing
        val parser = JsonEntitiesParser()
        val shop = parser.parse<ShopEntity>(shopsJson)

        assertNotNull(shop)
        assertEquals("Cortefiel - Preciados", shop.name)
    }

}