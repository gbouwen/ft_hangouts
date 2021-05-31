package com.example.ft_hangouts

import android.content.Intent
import android.os.Bundle
import android.os.SystemClock.sleep
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ft_hangouts.databinding.ContactDetailsBinding

class ContactDetails : AppCompatActivity() {

    private lateinit var binding: ContactDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ContactDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Edits the contact details and returns to MainActivity
        binding.editButton.setOnClickListener {
            editContact()
            sleep(500)
            finish()
        }

        // Deletes the contact and returns to MainActivity
        // TODO add confirm dialog on delete
        binding.deleteButton.setOnClickListener {
            deleteContact()
            sleep(500)
            finish()
        }

        binding.sendMessageButton.setOnClickListener {
            sendMessage()
        }
    }

    override fun onResume() {
        super.onResume()

        // Shows the values of the contact in the EditText fields
        showContactInfo()
    }

    // Gets the correct contact from the database by using the contact_id from the intent
    private fun showContactInfo() {
        val db = DataBase(this)
        val id = intent.getLongExtra("contact_id", 0)
        val contact = db.getContact(id)
        binding.firstNameInput.setText(contact.firstName)
        binding.lastNameInput.setText(contact.lastName)
        binding.companyInput.setText(contact.company)
        binding.phoneNumberInput.setText(contact.phoneNumber)
        binding.emailInput.setText(contact.email)
    }

    // Deletes the contact from the database
    private fun deleteContact() {
        val db = DataBase(this)
        val id = intent.getLongExtra("contact_id", 0)
        db.deleteContact(id)
        Toast.makeText(this, "Deleted contact", Toast.LENGTH_SHORT).show()
    }

    // Edits the contact data
    private fun editContact() {
        val db = DataBase(this)
        val contact = createContactFromInputTextFields()
        db.editContact(contact)
        Toast.makeText(this, "Edited contact", Toast.LENGTH_SHORT).show()
    }

    //Gets all the values of the InputText fields and creates a contact from it
    private fun createContactFromInputTextFields(): Contact {
        val id = intent.getLongExtra("contact_id", 0)
        val firstName = binding.firstNameInput.text.toString()
        val lastName = binding.lastNameInput.text.toString()
        val company = binding.companyInput.text.toString()
        val phoneNumber = binding.phoneNumberInput.text.toString()
        val email = binding.emailInput.text.toString()

        return (Contact(id, firstName, lastName, company, phoneNumber, email))
    }

    private fun sendMessage() {
        val intent = Intent(this, SendMessage::class.java)
        startActivity(intent)
    }
}