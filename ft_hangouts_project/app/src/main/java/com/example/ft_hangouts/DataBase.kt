package com.example.ft_hangouts

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataBase(context: Context) : SQLiteOpenHelper(context, "contacts.db", null, 1) {

    private val contactTable = "CONTACT_TABLE"
    private val columnFirstName = "FIRST_NAME"
    private val columnLastName = "LAST_NAME"
    private val columnCompany = "COMPANY"
    private val columnPhoneNumber = "PHONE_NUMBER"
    private val columnEmail = "EMAIL"

    // This function is called the first time a database is accessed. It creates the database.
    override fun onCreate(db: SQLiteDatabase?) {
        val createTableStatement: String = "CREATE TABLE $contactTable (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$columnFirstName TEXT, $columnLastName TEXT, $columnCompany TEXT, " +
                "$columnPhoneNumber TEXT, $columnEmail TEXT)"
        db?.execSQL(createTableStatement)
    }

    // This function is called if the database version number changes.
    // It prevents previous users apps from breaking when you change the database design.
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    //Adds the contact to the DataBase
    fun addToDatabase(contact: Contact): Boolean {
        val db = this.writableDatabase
        val cv = ContentValues()

        cv.put(columnFirstName, contact.firstName)
        cv.put(columnLastName, contact.lastName)
        cv.put(columnCompany, contact.company)
        cv.put(columnPhoneNumber, contact.phoneNumber)
        cv.put(columnEmail, contact.email)

        val insert = db.insert(contactTable, null, cv)
        if (insert == -1L)
            return false
        return true
    }

    fun getAllContacts(): List<Contact> {
        val list: MutableList<Contact> = ArrayList()
        val query = "SELECT * FROM $contactTable"
        val db = this.readableDatabase

        val cursor: Cursor = db.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            do {
                val firstName = cursor.getColumnName(0)
                val lastName = cursor.getColumnName(1)
                val company = cursor.getColumnName(2)
                val phoneNumber = cursor.getColumnName(3)
                val email = cursor.getColumnName(4)

                val contact = Contact(firstName, lastName, company, phoneNumber, email)
                list.add(contact)
            } while (cursor.moveToNext())
        }
        return list
    }
}