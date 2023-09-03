package ua.shtest.casestudy.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import androidx.room.Update
import io.reactivex.rxjava3.core.Single
import ua.shtest.casestudy.data.local.entity.ItemEntity

/**
 * @project Case Study
 * @author Stanislav Humeniuk on 03.09.2023
 * @email stanislav.humeniuk@gmail.com
 */

@Dao
interface ItemDao {
    @Query("SELECT * FROM items")
    fun getAll(): Single<List<ItemEntity>>

    @Insert(onConflict = REPLACE)
    fun insertAll(vararg items: ItemEntity): Single<List<Long>>

    @Delete
    fun delete(item: ItemEntity): Single<Int>

    @Update
    fun update(item: ItemEntity): Single<Int>
}