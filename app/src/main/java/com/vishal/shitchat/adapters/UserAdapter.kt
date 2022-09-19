package com.vishal.shitchat.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.vishal.shitchat.ChatActivity
import com.vishal.shitchat.models.User
import com.vishal.shitchat.R
import kotlinx.android.synthetic.main.user_item.view.*

class UserAdapter(private val context: Context, private val userList: ArrayList<User>) : RecyclerView.Adapter<UserAdapter.ChatViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.user_item,parent,false)
        return ChatViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.nameCA.text = userList[position].name
        holder.lastMessageCA.text = userList[position].lastMessage
        holder.lastSeenCA.text = userList[position].lastSeen

        //load profile image using Glide

        //on click
        holder.itemView.setOnClickListener {
            val i = Intent(context,ChatActivity::class.java)

            i.putExtra("NAME",userList[position].name)
            i.putExtra("ID",userList[position].id)

            context.startActivity(i)
        }
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