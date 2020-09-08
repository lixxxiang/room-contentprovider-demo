package com.example.contentprovider_demo

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Person::class], version = 1)
abstract class ApplicationDatabase : RoomDatabase() {
    abstract val personDao: PersonDao?

    companion object {
        private val DATABASE_NAME = "testdb"
        private var INSTANCE: ApplicationDatabase? = null
        fun getInstance(context: Context): ApplicationDatabase? {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context, ApplicationDatabase::class.java, DATABASE_NAME).build()
            }
            return INSTANCE
        }
    }
}

