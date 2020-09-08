package com.example.contentprovider_demo

import android.content.ContentValues
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {


    companion object {
        var PERSON_ID = "id"
        var PERSON_NAME = "name"
        var PERSON_GENDER = "gender"
        var PERSON_EMAIL_ADDRESS = "address"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun insert(view: View) {
        val uri_user = Uri.parse("content://com.example.contentprovider_demo/")
        val contentValues = ContentValues()
        contentValues.put(PERSON_ID, 1)
        contentValues.put(PERSON_NAME, "lixiang")
        contentValues.put(PERSON_GENDER, "male")
        contentValues.put(PERSON_EMAIL_ADDRESS, "changchun")

        val contentResolver = contentResolver
        contentResolver.insert(uri_user, contentValues)
    }

    fun delete(view: View) {

    }

    fun update(view: View) {

    }

    fun find(view: View) {

    }
}