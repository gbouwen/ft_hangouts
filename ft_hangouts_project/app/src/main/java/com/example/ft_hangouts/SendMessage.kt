package com.example.ft_hangouts

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SendMessage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.send_message)

    }

    override fun onResume() {
        super.onResume()

        showContactName()
    }

    // Puts the contactName after the To: field
    private fun showContactName() {
        val db = DataBase(this)
        val id = intent.getLongExtra("contact_id", 0)
        val contact = db.getContact(id)
        Toast.makeText(this, id.toString(), Toast.LENGTH_SHORT).show()
        val contactName: TextView = findViewById(R.id.send_contact_name)
        contactName.text = contact.firstName
    }
}