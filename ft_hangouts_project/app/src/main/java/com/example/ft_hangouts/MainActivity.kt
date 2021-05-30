package com.example.ft_hangouts

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Generates the list of all the contacts in the DataBase
        showAllContacts()

        // Go to AddUser activity
        val addButton: Button = findViewById(R.id.add_button)
        addButton.setOnClickListener {
            val intent = Intent(this, AddUser::class.java)
            startActivity(intent)
        }
    }

    private fun showAllContacts() {
        val db = DataBase(this)
        val allContacts = db.getAllContacts()
        val recyclerView = findViewById<RecyclerView>(R.id.contact_list)
    }
}