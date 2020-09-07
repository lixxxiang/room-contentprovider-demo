package com.example.contentprovider_demo

import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.annotation.Nullable


class PersonContentProvider : ContentProvider() {

    companion object {
        val uriMatcher = UriMatcher(UriMatcher.NO_MATCH)
        const val AUTHORITY = "com.example.contentprovider_demo.provider"
        const val PERSON_TABLE_NAME = "person_info"
        const val ID_PERSON_DATA = 1
        const val ID_PERSON_DATA_ITEM = 2
    }

    private lateinit var personDao: PersonDao
    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        when (uriMatcher.match(uri)) {
            ID_PERSON_DATA -> {
                if (context != null){
                    var id: Long = personDao.insert(Person.fromContentValues(values!!)) as Long
                    if(id as Int != 0){
                        context?.contentResolver?.notifyChange(uri, null)
                        return ContentUris.withAppendedId(uri, id)
                    }
                }
            }

            ID_PERSON_DATA_ITEM -> {
                Toast.makeText(context, "INSERT Failed", Toast.LENGTH_LONG).show()
            }

            else -> {
                Toast.makeText(context, "Failed", Toast.LENGTH_LONG).show()
            }
        }
    }



    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        var cursor: Cursor
        when (uriMatcher.match(uri)) {
            ID_PERSON_DATA -> {
                cursor = personDao.findAll()
                if (context != null) {
                    cursor.setNotificationUri(context?.contentResolver, uri)
                    return cursor
                }
            }
            else -> {
                Toast.makeText(context, "Failed", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onCreate(): Boolean {
        personDao = ApplicationDatabase.getInstance(context)?.personDao!!
        return false
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        when (uriMatcher.match(uri)) {
            ID_PERSON_DATA -> {
                if (context != null){
                    var count: Long = personDao.update(Person.fromContentValues(values!!)) as Long

                    if(count as Int != 0){
                        context?.contentResolver?.notifyChange(uri, null)
                        return count
                    }
                    throw IllegalArgumentException("Unknown URI:$uri")

                }
            }

            ID_PERSON_DATA_ITEM -> {
                Toast.makeText(context, "UPDATE Failed", Toast.LENGTH_LONG).show()
            }

            else -> {
                Toast.makeText(context, "Failed", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        when(uriMatcher.match(uri)){
            ID_PERSON_DATA -> throw IllegalArgumentException("CANNOT DELETE")
            ID_PERSON_DATA_ITEM -> {
                if (context!= null){
                    var count = personDao.delete(ContentUris.parseId(uri))
                    context?.contentResolver?.notifyChange(uri, null)
                    return count
                }
                throw IllegalArgumentException("Unknown URI:$uri")
            }
            else -> throw IllegalArgumentException("Failed")
        }
    }

    override fun getType(uri: Uri): String? {
        return null
    }
}