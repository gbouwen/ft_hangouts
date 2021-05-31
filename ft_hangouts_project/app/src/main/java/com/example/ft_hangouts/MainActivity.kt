package com.example.ft_hangouts

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Go to AddUser activity
        val addButton: Button = findViewById(R.id.add_button)
        addButton.setOnClickListener {
            val intent = Intent(this, AddContact::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()

        // Generates and shows the list of all the contacts in the DataBase
        showAllContacts()
    }

    private fun showAllContacts() {
        val db = DataBase(this)
        val allContacts = db.getAllContacts()
        val recyclerView = findViewById<RecyclerView>(R.id.contact_list)
        recyclerView.adapter = ContactAdapter(allContacts)
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
    }
}