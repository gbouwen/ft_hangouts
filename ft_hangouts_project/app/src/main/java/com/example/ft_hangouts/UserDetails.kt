package com.example.ft_hangouts

import android.os.Bundle
import android.os.SystemClock.sleep
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class UserDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_details)

        // Edits the user details and returns to MainActivity
        val saveButton: Button = findViewById(R.id.save_button)
        saveButton.setOnClickListener {
            // Add code for editing user in database
            sleep(500)
            finish()
        }

        // Deletes the user and returns to MainActivity
        // TODO add confirm dialog on delete
        val deleteButton: Button = findViewById(R.id.delete_button)
        deleteButton.setOnClickListener {
            // Add code for deleting user from database
            sleep(500)
            finish()
        }
    }
}