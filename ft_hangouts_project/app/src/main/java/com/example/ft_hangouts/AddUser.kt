package com.example.ft_hangouts

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class AddUser : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_user)

        // Add new user to database and go back to MainActivity
        val createButton: Button = findViewById(R.id.create_button)
        createButton.setOnClickListener {
            // Add code for adding the user to the database

            finish()
        }
    }
}