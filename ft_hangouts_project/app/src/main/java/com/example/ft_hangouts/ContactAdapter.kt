package com.example.ft_hangouts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactAdapter(private val contactList: List<Contact>)
    : RecyclerView.Adapter<ContactAdapter.ItemViewHolder>() {

    // Holds 1 item (contact_name)
    // TODO add multiple textViews to show all data
    class ItemViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.contact_name)
    }

    // Creates a new view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return ItemViewHolder(adapterLayout)
    }

    // Puts the contact name in the list
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.textView.text = contactList[position].firstName
    }

    // Returns size of list
    override fun getItemCount(): Int { return contactList.size}
}