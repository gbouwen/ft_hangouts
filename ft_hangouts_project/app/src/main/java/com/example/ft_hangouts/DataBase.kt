package com.example.ft_hangouts

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataBase(context: Context) : SQLiteOpenHelper(context, "contacts.db", null, 1) {

    private val contactTable = "CONTACT_TABLE"
    private val columnId = "ID"
    private val columnFirstName = "FIRST_NAME"
    private val columnLastName = "LAST_NAME"
    private val columnCompany = "COMPANY"
    private val columnPhoneNumber = "PHONE_NUMBER"
    private val columnEmail = "EMAIL"

    // This function is called the first time a database is accessed. It creates the database.
    override fun onCreate(db: SQLiteDatabase?) {
        val createTableStatement: String = "CREATE TABLE $contactTable ($columnId INTEGER PRIMARY KEY AUTOINCREMENT, " +
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

        val insertId = db.insert(contactTable, null, cv)
        if (insertId == -1L)
            return false
        contact.id = insertId
        return true
    }

    // Gets all contacts from the database and returns it as a list
    fun getAllContacts(): List<Contact> {
        // Create empty list
        val list: MutableList<Contact> = ArrayList()

        // Execute query to get cursor
        val db = this.readableDatabase
        val query = "SELECT * FROM $contactTable"
        val cursor = db.rawQuery(query, null)

        // Loop over all elements of the database and add them to the list
        if (cursor.moveToFirst()) {
            do {
                val contact = createContact(cursor)
                list.add(contact)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return list
    }

    // Function to create a single contact from a cursor
    private fun createContact(cursor: Cursor): Contact {
        val id = cursor.getLong(0)
        val firstName = cursor.getString(1)
        val lastName = cursor.getString(2)
        val company = cursor.getString(3)
        val phoneNumber = cursor.getString(4)
        val email = cursor.getString(5)

        return Contact(id, firstName, lastName, company, phoneNumber, email)
    }

    // Function to get a single contact from the id argument
    fun getContact(id: Long): Contact {
        val db = this.readableDatabase
        val query = "SELECT * FROM $contactTable WHERE $columnId = $id"
        val cursor = db.rawQuery(query, null)
        cursor.moveToNext()
        val contact = createContact(cursor)
        cursor.close()
        return contact
    }

    // Deletes a contact from the database
    fun deleteContact(id: Long) {
        val db = this.writableDatabase
        val query = "DELETE FROM $contactTable WHERE $columnId = $id"
        val cursor = db.rawQuery(query, null)
        cursor.moveToFirst()
        cursor.close()
    }

    // Updates the data of the contact
    fun updateContact(contact: Contact) {
        val db = this.writableDatabase
        val query = "UPDATE $contactTable " +
                "SET $columnFirstName = '${contact.firstName}', " +
                "$columnLastName = '${contact.lastName}', " +
                "$columnCompany = '${contact.company}', " +
                "$columnPhoneNumber = '${contact.phoneNumber}', " +
                "$columnEmail = '${contact.email}' " +
                "WHERE $columnId = ${contact.id}"
        val cursor = db.rawQuery(query, null)
        cursor.moveToFirst()
        cursor.close()
    }
}