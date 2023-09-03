package ua.shtest.casestudy.di.database

import android.content.Context
import androidx.room.Room.databaseBuilder
import dagger.Module
import dagger.Provides
import ua.shtest.casestudy.data.local.dao.ItemDao
import ua.shtest.casestudy.data.local.database.AppDatabase
import javax.inject.Singleton


/**
 * @project Case Study
 * @author Stanislav Humeniuk on 03.09.2023
 * @email stanislav.humeniuk@gmail.com
 */

@Module
class RoomModule(private val context: Context) {
    @Singleton
    @Provides
    fun provideRoomDatabase(): AppDatabase =
        databaseBuilder(context, AppDatabase::class.java, "case_study_database").build();

    @Singleton
    @Provides
    fun provideItemDao(appDatabase: AppDatabase): ItemDao = appDatabase.itemDao()
}