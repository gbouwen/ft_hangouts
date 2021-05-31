package com.example.ft_hangouts

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ft_hangouts.databinding.AddContactBinding

class AddContact : AppCompatActivity() {

    private lateinit var binding: AddContactBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AddContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Add new user to database, return back to MainActivity
        binding.createButton.setOnClickListener { createNewUser(binding) }
    }

    private fun createNewUser(binding: AddContactBinding) {
        // Create new contact with the values from the input fields
        val contact = Contact( -1L,
            binding.firstNameInput.text.toString().trim(),
            binding.lastNameInput.text.toString().trim(),
            binding.companyInput.text.toString().trim(),
            binding.phoneNumberInput.text.toString().trim(),
            binding.emailInput.text.toString().trim()
        )

        // Validates user input and shows error in TextView
        val error = contact.validateInput()
        if (error != "ok") {
            binding.addErrorMessage.text = error
            return
        }

        // Get DataBase instance and add Contact to DataBase
        val db = DataBase(this)
        db.addToDatabase(contact)
        finish()
    }
}