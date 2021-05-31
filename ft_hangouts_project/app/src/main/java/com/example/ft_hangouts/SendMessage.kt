package com.example.ft_hangouts

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.telephony.SmsManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

class SendMessage : AppCompatActivity() {

    private lateinit var contact: Contact

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.send_message)

        val sendButton: Button = findViewById(R.id.send_button)
        sendButton.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
                val message: EditText = findViewById(R.id.message_body)
                val messageString = message.text.toString().trim()
                if (messageString.isNotEmpty()) {
                    sendSMS(messageString)
                }
            } else {
                requestPermissions(arrayOf(Manifest.permission.SEND_SMS), 1)
            }
        }
    }

    override fun onResume() {
        super.onResume()

        showContactName()
    }

    // Puts the contactName after the To: field
    private fun showContactName() {
        val db = DataBase(this)
        val id = intent.getLongExtra("contact_id", 0)
        contact = db.getContact(id)
        val firstName: TextView = findViewById(R.id.send_first_name)
        firstName.text = contact.firstName
        val lastName: TextView = findViewById(R.id.send_last_name)
        lastName.text = contact.lastName
        val phoneNumber: TextView = findViewById(R.id.send_phone_number)
        phoneNumber.text = contact.phoneNumber
    }

    // Sends the SMS to the contact
    private fun sendSMS(message: String) {
        // Gets the smsManager and sends the message parameter to the contact as a text message
        try {
            val smsManager = SmsManager.getDefault()
            smsManager.sendTextMessage(contact.phoneNumber, null, message, null, null)
            Toast.makeText(this, "Message sent", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this, "Failed to send message", Toast.LENGTH_SHORT).show()
        }

        // Returns to the mainActivity
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}