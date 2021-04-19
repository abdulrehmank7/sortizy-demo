package com.arkapp.sortizydemo.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.arkapp.sortizydemo.data.models.UserLogin

/**
 * Created by Abdul Rehman on 19-04-2021.
 * Contact email - abdulrehman0796@gmail.com
 */


@Database(entities = [UserLogin::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userLoginDao(): UserLoginDao

    companion object {

        fun getDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java, "SORTIZY_DB"
            ).build()
        }
    }
}