package com.example.contentprovider_demo

import android.database.Cursor
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface PersonDao {
    @Insert
    fun insert(person: Person)

    @Query("SELECT * FROM person_info")
    fun findAll(): Cursor

    @Query("DELETE FROM person_info WHERE id=:id")
    fun delete(id: Long): Int

    @Update
    fun update(person: Person): Int

}