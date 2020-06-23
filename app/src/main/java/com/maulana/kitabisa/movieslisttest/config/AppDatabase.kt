package com.maulana.kitabisa.movieslisttest.config

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.maulana.kitabisa.movieslisttest.model.FavoritTable
import com.maulana.kitabisa.movieslisttest.repository.FavoritDao

/**
 * @author Maulana Rahmatullah
 * @describe Database Room configuration
 */
@Database(version = 1, entities = [FavoritTable::class])
abstract class AppDatabase : RoomDatabase() {
    abstract fun favDao(): FavoritDao

    companion object{
        var INSTANCE: AppDatabase? = null

        fun getDb(context: Context): AppDatabase? {
            if (INSTANCE == null){
                synchronized(AppDatabase::class){
                    INSTANCE = Room.databaseBuilder(context, AppDatabase::class.java, "movielist").allowMainThreadQueries().build()
                }
            }
            return INSTANCE
        }
    }
}