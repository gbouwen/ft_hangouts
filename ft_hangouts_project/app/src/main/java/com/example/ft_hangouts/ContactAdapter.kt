package com.example.ft_hangouts

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class ContactAdapter(private val contactList: List<Contact>)
    : RecyclerView.Adapter<ContactAdapter.ItemViewHolder>() {
    
    // Holds 2 item (contact_name and phone_number)
    inner class ItemViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        val contactName: TextView = view.findViewById(R.id.list_contact_name)
        val phoneNumber: TextView = view.findViewById(R.id.list_phone_number)

        // Makes the list items clickable
        init {
            view.setOnClickListener {
                // Change background color on click
                val backgroundColor = ContextCompat.getColor(view.context, R.color.background)
                view.setBackgroundColor(backgroundColor)

                // Go to contact details with the right contact_id
                val position: Int = adapterPosition
                val intent = Intent(view.context, ContactDetails::class.java)
                intent.putExtra("contact_id", contactList[position].id)
                view.context.startActivity(intent)
            }
        }
    }

    // Creates a new view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return ItemViewHolder(adapterLayout)
    }

    // Puts the contact name in the list
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val contact = contactList[position]
        holder.contactName.text = contact.firstName
        holder.phoneNumber.text= contact.phoneNumber
    }

    // Returns size of list
    override fun getItemCount(): Int { return contactList.size}
}