package com.example.contentprovider_demo

import android.content.ContentValues
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "person_info")
data class Person(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "gender")
    var genter: String,
    @ColumnInfo(name = "emailAddress")
    var emailAddress: String
) {
    companion object {
        fun fromContentValues(contentValues: ContentValues): Person {
            var person: Person = Person()
            if (contentValues.containsKey(PERSON_ID)) {
                person.id = contentValues.getAsInteger(PERSON_ID)
            } else if (contentValues.containsKey(PERSON_NAME)) {
                person.name = contentValues.getAsString(PERSON_NAME)
            } else if (contentValues.containsKey(PERSON_GENDER)) {
                person.genter = contentValues.getAsString(PERSON_GENDER)
            } else if (contentValues.containsKey(PERSON_EMAIL_ADDRESS)) {
                person.emailAddress = contentValues.getAsString(PERSON_EMAIL_ADDRESS)
            }
            return person
        }
    }
}