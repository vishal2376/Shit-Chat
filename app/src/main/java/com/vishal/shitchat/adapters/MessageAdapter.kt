package com.vishal.shitchat.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.vishal.shitchat.R
import com.vishal.shitchat.models.Message
import kotlinx.android.synthetic.main.receiver_chat.view.*
import kotlinx.android.synthetic.main.sender_chat.view.*

class MessageAdapter(val context: Context, private val messageList: List<Message>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var isSender = 1 // 1 -> true

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if (viewType == 1) {
            //inflate sender chat layout
            val view = LayoutInflater.from(context).inflate(R.layout.sender_chat, parent, false)
            return SendViewHolder(view)
        } else {
            //inflate receiver chat layout
            val view = LayoutInflater.from(context).inflate(R.layout.receiver_chat, parent, false)
            return ReceiveViewHolder(view)
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentMessage = messageList[position]

        if (holder.javaClass == SendViewHolder::class.java) {
            //sender chat
            val viewHolder = holder as SendViewHolder
            viewHolder.sentMessage.text = currentMessage.message
        } else {
            //receiver chat
            val viewHolder = holder as ReceiveViewHolder
            viewHolder.receiveMessage.text = currentMessage.message

        }
    }

    override fun getItemViewType(position: Int): Int {
        val currentMessage = messageList[position]

        if (FirebaseAuth.getInstance().currentUser!!.uid == currentMessage.id) {
            isSender = 1
        } else {
            isSender = 0
        }
        return isSender
    }

    override fun getItemCount(): Int {
        return messageList.size
    }

    class SendViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val sentMessage: TextView = itemView.tvMessageSC
    }

    class ReceiveViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val receiveMessage: TextView = itemView.tvMessageRC
    }

}