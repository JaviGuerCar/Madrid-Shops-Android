package madridshops.kc.com.repository.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

fun build(context: Context, name: String, version: Int): DBHelper {
    return DBHelper(context, name, null, version)
}

class DBHelper(
        context: Context?,
        name: String?,
        factory: SQLiteDatabase.CursorFactory?,
        version: Int)
    : SQLiteOpenHelper(context, name, factory, version) {

    override fun onOpen(db: SQLiteDatabase?) {
        super.onOpen(db)

        // We need this for ON CASCADE deletion to work properly
        db?.setForeignKeyConstraintsEnabled(true)

    }

    override fun onCreate(db: SQLiteDatabase?) {
        DBConstants.CREATE_DATABASE_SCRIPTS.forEach { db?.execSQL(it) }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

}


// Helpers

fun convert(boolean: Boolean): Int {
    if (boolean) {
        return 1
    }
    return 0
}

fun convert(int: Int): Boolean {
    if (int == 0) {
        return false
    }
    return true
}