package com.example.ft_hangouts

import android.os.Bundle
import android.os.SystemClock.sleep
import androidx.appcompat.app.AppCompatActivity
import com.example.ft_hangouts.databinding.AddUserBinding

class AddUser : AppCompatActivity() {

    private lateinit var binding: AddUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AddUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Add new user to database, return back to MainActivity
        binding.createButton.setOnClickListener { createNewUser(binding) }
    }

    private fun createNewUser(binding: AddUserBinding) {
        // Create new contact with the values from the input fields
        val contact = Contact( -1,
            binding.firstNameInput.text.toString(),
            binding.lastNameInput.text.toString(),
            binding.companyInput.text.toString(),
            binding.phoneNumberInput.text.toString(),
            binding.emailInput.text.toString()
        )

        // Phone number and first name can't be empty
        // TODO GIVE CLEAR ERROR MESSAGE IF EMPTY
        if (contact.phoneNumber.isEmpty() || contact.firstName.isEmpty())
            return

        // Get DataBase instance and add Contact to DataBase
        val dataBaseHelper = DataBase(this)
        dataBaseHelper.addToDatabase(contact)
        sleep(500)
        finish()
    }
}