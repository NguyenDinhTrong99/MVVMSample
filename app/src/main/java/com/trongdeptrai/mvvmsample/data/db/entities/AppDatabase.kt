package com.trongdeptrai.mvvmsample.data.db.entities

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.trongdeptrai.mvvmsample.data.db.UserDAO

@Database(
    entities = [User::class],
    version = 1
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getUserDao(): UserDAO

    companion object{
        @Volatile
        private var instance: AppDatabase? = null
        private var LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                  AppDatabase::class.java,
                "MyDatabase.db"
            ).build()
    }
}