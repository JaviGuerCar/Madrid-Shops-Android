package com.kc.madridshops.repository.db.dao

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.kc.madridshops.repository.db.DBConstants
import com.kc.madridshops.repository.db.DBHelper
import com.kc.madridshops.repository.model.ShopEntity

internal class ShopDAO (val dbHelper: DBHelper): DAOPersistable<ShopEntity> {

    private val dbReadOnlyConnection: SQLiteDatabase = dbHelper.readableDatabase
    private val dbReadWriteConnection: SQLiteDatabase = dbHelper.writableDatabase


    override fun insert(element: ShopEntity): Long {
        var id: Long = 0
        id = dbReadWriteConnection.insert(DBConstants.TABLE_SHOP, null, contentValues(element))

        // Close DB
        close()

        return id
    }

    // contentValues is as a Dictionary (key, value)
    fun contentValues (shopEntity: ShopEntity): ContentValues {
        val content = ContentValues()

        // Put key-values in content
        content.put(DBConstants.KEY_SHOP_JSON_ID, shopEntity.id)
        content.put(DBConstants.KEY_SHOP_NAME, shopEntity.name)
        content.put(DBConstants.KEY_SHOP_ADDRESS, shopEntity.address)
        content.put(DBConstants.KEY_SHOP_DESCRIPTION_EN, shopEntity.description_en)
        content.put(DBConstants.KEY_SHOP_DESCRIPTION_ES, shopEntity.description_es)
        content.put(DBConstants.KEY_SHOP_LATITUDE, shopEntity.latitude)
        content.put(DBConstants.KEY_SHOP_LONGITUDE, shopEntity.longitude)
        content.put(DBConstants.KEY_SHOP_IMAGE_URL, shopEntity.img)
        content.put(DBConstants.KEY_SHOP_LOGO_IMAGE_URL, shopEntity.logo)
        content.put(DBConstants.KEY_SHOP_OPENING_HOURS_EN, shopEntity.openingHours_en)
        content.put(DBConstants.KEY_SHOP_OPENING_HOURS_ES, shopEntity.openingHours_es)
        content.put(DBConstants.KEY_SHOP_URL, shopEntity.url)

        return content
    }

    override fun delete(element: ShopEntity): Long {

        if (element.databaseId < 1){
            return 0
        }

        return delete(element.databaseId)
    }

    override fun delete(id: Long): Long {
        val deleteId = dbReadWriteConnection.delete(DBConstants.TABLE_SHOP, DBConstants.KEY_SHOP_DATABASE_ID + " = ?", arrayOf(id.toString())).toLong()

        // Close DB
        close()

        return deleteId
    }

    override fun deleteAll(): Boolean {
        val deleted = dbReadWriteConnection.delete(
                DBConstants.TABLE_SHOP,
                null,
                null
                ).toLong() >= 0 // Devuelvo true si he borrado algún registro

        // Close DB
        close()

        return deleted
    }


    override fun query(id: Long): ShopEntity {
        val cursor = queryCursor(id)

        cursor.moveToFirst()

        return entityFromCursor(cursor)!!

    }

    override fun query(): List<ShopEntity> {

        val queryResult = ArrayList<ShopEntity>()

        val cursor = dbReadOnlyConnection.query(DBConstants.TABLE_SHOP,
                DBConstants.ALL_COLUMNS,
                null,
                null,
                "",
                "",
                DBConstants.KEY_SHOP_DATABASE_ID
        )

        while (cursor.moveToNext()) {
            val se = entityFromCursor(cursor)
            queryResult.add(se!!)
        }

        close()

        return queryResult
    }

    // Función para recorrer el cursor y devolver un ShopEntity con los datos
    fun entityFromCursor(cursor: Cursor): ShopEntity? {

        // Si está después del último y antes del primero, no devuelvo nada
        if (cursor.isAfterLast || cursor.isBeforeFirst) {
            return null
        }

        return ShopEntity(
                cursor.getLong(cursor.getColumnIndex(DBConstants.KEY_SHOP_JSON_ID)),
                cursor.getLong(cursor.getColumnIndex(DBConstants.KEY_SHOP_DATABASE_ID)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_SHOP_NAME)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_SHOP_DESCRIPTION_EN)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_SHOP_DESCRIPTION_ES)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_SHOP_LATITUDE)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_SHOP_LONGITUDE)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_SHOP_IMAGE_URL)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_SHOP_LOGO_IMAGE_URL)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_SHOP_OPENING_HOURS_EN)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_SHOP_OPENING_HOURS_ES)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_SHOP_ADDRESS)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_SHOP_URL))
        )

    }

    override fun queryCursor(id: Long): Cursor {
        val cursor = dbReadOnlyConnection.query(DBConstants.TABLE_SHOP,
                DBConstants.ALL_COLUMNS,
                DBConstants.KEY_SHOP_DATABASE_ID + " = ? ",
                arrayOf(id.toString()),
                "",
                "",
                DBConstants.KEY_SHOP_DATABASE_ID
        )

        close()

        return cursor
    }

    override fun update(id: Long, element: ShopEntity): Long {
        val numberOfRecordsUpdated = dbReadWriteConnection.update(
                DBConstants.TABLE_SHOP,
                contentValues(element),
                DBConstants.KEY_SHOP_DATABASE_ID + " = ?",
                arrayOf(id.toString())
        )

        close()
        return numberOfRecordsUpdated.toLong()
    }

    // Function to close DB
    private fun close(){
        dbReadOnlyConnection.close()
        dbReadWriteConnection.close()
    }


}