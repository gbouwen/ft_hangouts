package com.example.ft_hangouts

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ft_hangouts.databinding.ContactDetailsBinding

class ContactDetails : AppCompatActivity() {

    private lateinit var binding: ContactDetailsBinding
    private lateinit var db: DataBase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ContactDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get DataBase object
        db = DataBase(this)

        // Updates the contact details and returns to MainActivity
        binding.updateButton.setOnClickListener { updateContact() }

        // Deletes the contact and returns to MainActivity
        binding.deleteButton.setOnClickListener { deleteContact() }

        // Goes to sendMessage activity
        binding.sendMessageButton.setOnClickListener { sendMessage() }
    }

    override fun onResume() {
        super.onResume()

        // Shows the values of the contact in the EditText fields
        showContactInfo()
    }

    // Gets the correct contact from the database by using the contact_id from the intent
    private fun showContactInfo() {
        val id = intent.getLongExtra("contact_id", 0)
        val contact = db.getContact(id)

        binding.editFirstNameInput.setText(contact.firstName)
        binding.editLastNameInput.setText(contact.lastName)
        binding.editCompanyInput.setText(contact.company)
        binding.editPhoneNumberInput.setText(contact.phoneNumber)
        binding.editEmailInput.setText(contact.email)
    }

    // Deletes the contact from the database
    private fun deleteContact() {
        val db = DataBase(this)
        val id = intent.getLongExtra("contact_id", 0)
        db.deleteContact(id)
        Toast.makeText(this, "Contact deleted", Toast.LENGTH_SHORT).show()
        finish()
    }

    // Edits the contact data
    private fun updateContact() {
        val contact = createContactFromInputTextFields()
        val error = contact.validateInput()
        if (error != "ok") {
            binding.editErrorMessage.text = error
            return
        }
        db.updateContact(contact)
        Toast.makeText(this, "Contact updated", Toast.LENGTH_SHORT).show()
        finish()
    }

    //Gets all the values of the InputText fields and creates a contact from it
    private fun createContactFromInputTextFields(): Contact {
        val id = intent.getLongExtra("contact_id", 0)
        val firstName = binding.editFirstNameInput.text.toString().trim()
        val lastName = binding.editLastNameInput.text.toString().trim()
        val company = binding.editCompanyInput.text.toString().trim()
        val phoneNumber = binding.editPhoneNumberInput.text.toString().trim()
        val email = binding.editEmailInput.text.toString().trim()

        return (Contact(id, firstName, lastName, company, phoneNumber, email))
    }

    // Sends id to SendMessage Activity and starts it
    private fun sendMessage() {
        val id = intent.getLongExtra("contact_id", 0)
        val sendMessageIntent = Intent(this, SendMessage::class.java)
        sendMessageIntent.putExtra("contact_id", id)
        startActivity(sendMessageIntent)
    }
}