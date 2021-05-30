package com.example.ft_hangouts

import android.os.Bundle
import android.os.SystemClock.sleep
import androidx.appcompat.app.AppCompatActivity
import com.example.ft_hangouts.databinding.ContactDetailsBinding

class ContactDetails : AppCompatActivity() {

    private lateinit var binding: ContactDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ContactDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Edits the user details and returns to MainActivity
        binding.editButton.setOnClickListener {
            // Add code for editing user in database
            sleep(500)
            finish()
        }

        // Deletes the user and returns to MainActivity
        // TODO add confirm dialog on delete
        binding.deleteButton.setOnClickListener {
            deleteUser()
            sleep(500)
            finish()
        }
    }

    override fun onResume() {
        super.onResume()

        // Shows the values of the user in the EditText fields
        showUserInfo()
    }

    // Gets the correct user from the database by using the contact_id from the intent
    private fun showUserInfo() {
        val db = DataBase(this)
        val id = intent.getLongExtra("contact_id", 0)
        val contact = db.getContact(id)
        binding.firstNameInput.setText(contact.firstName)
        binding.lastNameInput.setText(contact.lastName)
        binding.companyInput.setText(contact.company)
        binding.phoneNumberInput.setText(contact.phoneNumber)
        binding.emailInput.setText(contact.email)
    }

    // Deletes the user from the database
    private fun deleteUser() {
        val db = DataBase(this)
        val id = intent.getLongExtra("contact_id", 0)
        db.deleteContact(id)
    }
}