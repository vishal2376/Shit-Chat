package com.vishal.shitchat.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vishal.shitchat.models.User
import com.vishal.shitchat.R
import kotlinx.android.synthetic.main.chat_item.view.*

class UserAdapter(private val context: Context, private val userList: ArrayList<User>) : RecyclerView.Adapter<UserAdapter.ChatViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.chat_item,parent,false)
        return ChatViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.nameCA.text = userList[position].name
        holder.lastMessageCA.text = userList[position].lastMessage
        holder.lastSeenCA.text = userList[position].lastSeen

        //load profile image using Glide
    }

    override fun getItemCount(): Int {
        return userList.size
    }


    class ChatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val profileCA = itemView.imgProfileCI
        val nameCA = itemView.tvNameCI
        val lastMessageCA = itemView.tvLastMessageCI
        val lastSeenCA = itemView.tvLastSeenCI
    }

}