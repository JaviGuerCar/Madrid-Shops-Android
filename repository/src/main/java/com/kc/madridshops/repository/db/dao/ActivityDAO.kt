package com.kc.madridshops.repository.db.dao

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.kc.madridshops.repository.db.DBConstants
import com.kc.madridshops.repository.db.DBHelper
import com.kc.madridshops.repository.model.ActivityEntity

internal class ActivityDAO(val dbHelper: DBHelper): DAOPersistable<ActivityEntity> {

    private val dbReadOnlyConnection: SQLiteDatabase = dbHelper.readableDatabase
    private val dbReadWriteConnection: SQLiteDatabase = dbHelper.writableDatabase


    override fun insert(element: ActivityEntity): Long {
        var id: Long = 0
        id = dbReadWriteConnection.insert(DBConstants.TABLE_ACTIVITY, null, contentValues(element))

        // Close DB
        close()

        return id
    }

    // contentValues is as a Dictionary (key, value)
    fun contentValues (activityEntity: ActivityEntity): ContentValues {
        val content = ContentValues()

        // Put key-values in content
        content.put(DBConstants.KEY_ACTIVITY_JSON_ID, activityEntity.id)
        content.put(DBConstants.KEY_ACTIVITY_NAME, activityEntity.name)
        content.put(DBConstants.KEY_ACTIVITY_ADDRESS, activityEntity.address)
        content.put(DBConstants.KEY_ACTIVITY_DESCRIPTION_EN, activityEntity.description_en)
        content.put(DBConstants.KEY_ACTIVITY_DESCRIPTION_ES, activityEntity.description_es)
        content.put(DBConstants.KEY_ACTIVITY_LATITUDE, activityEntity.latitude)
        content.put(DBConstants.KEY_ACTIVITY_LONGITUDE, activityEntity.longitude)
        content.put(DBConstants.KEY_ACTIVITY_IMAGE_URL, activityEntity.img)
        content.put(DBConstants.KEY_ACTIVITY_LOGO_IMAGE_URL, activityEntity.logo)
        content.put(DBConstants.KEY_ACTIVITY_OPENING_HOURS_EN, activityEntity.openingHours_en)
        content.put(DBConstants.KEY_ACTIVITY_OPENING_HOURS_ES, activityEntity.openingHours_es)
        content.put(DBConstants.KEY_ACTIVITY_URL, activityEntity.url)

        return content
    }

    override fun delete(element: ActivityEntity): Long {

        if (element.databaseId < 1){
            return 0
        }

        return delete(element.databaseId)
    }

    override fun delete(id: Long): Long {
        val deleteId = dbReadWriteConnection.delete(DBConstants.TABLE_ACTIVITY, DBConstants.KEY_ACTIVITY_DATABASE_ID + " = ?", arrayOf(id.toString())).toLong()

        // Close DB
        close()

        return deleteId
    }

    override fun deleteAll(): Boolean {
        val deleted = dbReadWriteConnection.delete(
                DBConstants.TABLE_ACTIVITY,
                null,
                null
                ).toLong() >= 0 // Devuelvo true si he borrado algún registro

        // Close DB
        close()

        return deleted
    }


    override fun query(id: Long): ActivityEntity {
        val cursor = queryCursor(id)

        cursor.moveToFirst()

        return entityFromCursor(cursor)!!

    }

    override fun query(): List<ActivityEntity> {

        val queryResult = ArrayList<ActivityEntity>()

        val cursor = dbReadOnlyConnection.query(DBConstants.TABLE_ACTIVITY,
                DBConstants.ALL_ACTIVITY_COLUMNS,
                null,
                null,
                "",
                "",
                DBConstants.KEY_ACTIVITY_DATABASE_ID
        )

        while (cursor.moveToNext()) {
            val se = entityFromCursor(cursor)
            queryResult.add(se!!)
        }

        close()

        return queryResult
    }

    // Función para recorrer el cursor y devolver un ShopEntity con los datos
    fun entityFromCursor(cursor: Cursor): ActivityEntity? {

        // Si está después del último y antes del primero, no devuelvo nada
        if (cursor.isAfterLast || cursor.isBeforeFirst) {
            return null
        }

        return ActivityEntity(
                cursor.getLong(cursor.getColumnIndex(DBConstants.KEY_ACTIVITY_JSON_ID)),
                cursor.getLong(cursor.getColumnIndex(DBConstants.KEY_ACTIVITY_DATABASE_ID)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_ACTIVITY_NAME)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_ACTIVITY_DESCRIPTION_EN)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_ACTIVITY_DESCRIPTION_ES)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_ACTIVITY_LATITUDE)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_ACTIVITY_LONGITUDE)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_ACTIVITY_IMAGE_URL)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_ACTIVITY_LOGO_IMAGE_URL)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_ACTIVITY_OPENING_HOURS_EN)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_ACTIVITY_OPENING_HOURS_ES)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_ACTIVITY_ADDRESS)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_ACTIVITY_URL))
        )

    }

    override fun queryCursor(id: Long): Cursor {
        val cursor = dbReadOnlyConnection.query(DBConstants.TABLE_ACTIVITY,
                DBConstants.ALL_ACTIVITY_COLUMNS,
                DBConstants.KEY_ACTIVITY_DATABASE_ID + " = ? ",
                arrayOf(id.toString()),
                "",
                "",
                DBConstants.KEY_ACTIVITY_DATABASE_ID
        )

        close()

        return cursor
    }

    override fun update(id: Long, element: ActivityEntity): Long {
        val numberOfRecordsUpdated = dbReadWriteConnection.update(
                DBConstants.TABLE_ACTIVITY,
                contentValues(element),
                DBConstants.KEY_ACTIVITY_DATABASE_ID + " = ?",
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