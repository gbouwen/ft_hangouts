package com.example.ft_hangouts

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataBase(context: Context) : SQLiteOpenHelper(context, "contacts.db", null, 1) {

    val CONTACT_TABLE = "CONTACT_TABLE"
    val COLUMN_FIRST_NAME = "FIRST_NAME"
    val COLUMN_LAST_NAME = "LAST_NAME"
    val COLUMN_COMPANY = "COMPANY"
    val COLUMN_PHONE_NUMBER = "PHONE_NUMBER"
    val COLUMN_EMAIL = "EMAIL"

    // This function is called the first time a database is accessed. It creates the database.
    override fun onCreate(db: SQLiteDatabase?) {
        val createTableStatement: String = "CREATE TABLE " + CONTACT_TABLE +
                " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_FIRST_NAME + " TEXT, " +
                COLUMN_LAST_NAME + " TEXT, " + COLUMN_COMPANY + " TEXT, " +
                COLUMN_PHONE_NUMBER + " TEXT, " + COLUMN_EMAIL + " TEXT)"
        db?.execSQL(createTableStatement)
    }

    // This function is called if the database version number changes.
    // It prevents previous users apps from breaking when you change the database design.
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    //Adds the contact to the DataBase
    fun addToDatabase(contact: Contact) : Boolean {
        val db = this.writableDatabase
        val cv = ContentValues()

        cv.put(COLUMN_FIRST_NAME, contact.firstName)
        cv.put(COLUMN_LAST_NAME, contact.lastName)
        cv.put(COLUMN_COMPANY, contact.company)
        cv.put(COLUMN_PHONE_NUMBER, contact.phoneNumber)
        cv.put(COLUMN_EMAIL, contact.email)

        val insert = db.insert(CONTACT_TABLE, null, cv)
        if (insert == -1L)
            return false
        return true
    }
}