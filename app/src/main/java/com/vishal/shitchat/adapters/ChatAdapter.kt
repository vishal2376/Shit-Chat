package com.vishal.shitchat.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vishal.shitchat.Person
import com.vishal.shitchat.R
import kotlinx.android.synthetic.main.chat_item.view.*

class ChatAdapter(private val context: Context, private val personList: ArrayList<Person>) : RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.chat_item,parent,false)
        return ChatViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.nameCA.text = personList[position].name
        holder.lastMessageCA.text = personList[position].lastMessage
        holder.lastSeenCA.text = personList[position].lastSeen

        //load profile image using Glide
    }

    override fun getItemCount(): Int {
        return personList.size
    }


    class ChatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val profileCA = itemView.imgProfileCI
        val nameCA = itemView.tvNameCI
        val lastMessageCA = itemView.tvLastMessageCI
        val lastSeenCA = itemView.tvLastSeenCI
    }

}