package com.kc.madridshops

import com.fasterxml.jackson.databind.exc.InvalidFormatException
import com.kc.madridshops.repository.model.ShopEntity
import com.kc.madridshops.repository.model.ShopsResponseEntity
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

        val shopJson = ReadJsonFile().loadJSONFromAsset("shop.json")

        assertTrue(!shopJson.isEmpty())

        // test parsing
        val parser = JsonEntitiesParser()
        val shop = parser.parse<ShopEntity>(shopJson)

        assertNotNull(shop)
        assertEquals("Cortefiel - Preciados", shop.name)
        //assertEquals(40.4180563f, shop.latitude, 0.1f)
    }

    @Test
    @Throws(Exception::class)
    fun given_invalid_string_containing_json_with_wrong_latitude_it_parses_correctly() {

        val shopJson = ReadJsonFile().loadJSONFromAsset("shopWrongLatitude.json")

        assertTrue(!shopJson.isEmpty())

        // test parsing
        val parser = JsonEntitiesParser()
        var shop: ShopEntity
        try {
            shop = parser.parse<ShopEntity>(shopJson)

        } catch (e: InvalidFormatException) {
            shop = ShopEntity(1,1,"Parsing failed","",10f,12f,"","","","")
        }

        assertNotNull(shop)
        assertEquals("Parsing failed", shop.name)
        //assertEquals(10f, shop.latitude, 0.1f)
    }

    @Test
    @Throws(Exception::class)
    fun given_valid_string_containing_json_shops_it_parses_correctly_all_shops() {

        val shopsJson = ReadJsonFile().loadJSONFromAsset("someShops.json")

        assertTrue(!shopsJson.isEmpty())

        // test parsing
        val parser = JsonEntitiesParser()
        val shopsresponseentity = parser.parse<ShopsResponseEntity>(shopsJson)

        assertNotNull(shopsresponseentity)
        assertEquals(6, shopsresponseentity.result.count())
        assertEquals("Cortefiel - Preciados", shopsresponseentity.result[0].name)
        //assertEquals(40.4180563f, shopsresponseentity.result[0].latitude, 0.1f)
    }

}