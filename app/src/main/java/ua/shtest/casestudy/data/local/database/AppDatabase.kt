package ua.shtest.casestudy.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ua.shtest.casestudy.data.local.dao.ItemDao
import ua.shtest.casestudy.data.local.entity.ItemEntity

/**
 * @project Case Study
 * @author Stanislav Humeniuk on 03.09.2023
 * @email stanislav.humeniuk@gmail.com
 */

@Database(entities = [ItemEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun itemDao(): ItemDao
}