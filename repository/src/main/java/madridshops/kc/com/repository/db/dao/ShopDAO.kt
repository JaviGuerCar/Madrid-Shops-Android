package madridshops.kc.com.repository.db.dao

import android.database.Cursor
import madridshops.kc.com.repository.model.ShopEntity

class ShopDAO: DAOPersistable<ShopEntity> {
    override fun query(id: Int): ShopEntity {
    }

    override fun query(): List<ShopEntity> {
    }

    override fun queryCursor(): Cursor {
    }

    override fun insert() {
    }

    override fun update() {
    }

    override fun delete() {
    }

    override fun deleteAll() {
    }

}