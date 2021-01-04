package io.github.jesterz91.finedust.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import io.github.jesterz91.finedust.util.Constant

@Database(entities = [Location::class], version = 1)
abstract class LocationDatabase : RoomDatabase() {

    abstract fun locationDao(): LocationDao

    companion object {
        @Volatile
        private var INSTANCE: LocationDatabase? = null

        fun getInstance(context: Context): LocationDatabase = INSTANCE ?: synchronized(this) {
            INSTANCE ?: Room.databaseBuilder(context, LocationDatabase::class.java, Constant.DATABASE_NAME).build().also {
                INSTANCE = it
            }
        }
    }
}