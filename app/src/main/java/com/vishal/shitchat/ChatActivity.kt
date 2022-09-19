package com.vishal.shitchat

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.vishal.shitchat.adapters.MessageAdapter
import com.vishal.shitchat.models.Message
import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.android.synthetic.main.receiver_chat.*

class ChatActivity : AppCompatActivity() {

    private lateinit var messageList: ArrayList<Message>
    private lateinit var dbRef: DatabaseReference

    var senderRoom: String? = null
    var receiverRoom: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        //initialize
        messageList = ArrayList()
        rvMessageListCA.adapter = MessageAdapter(this, messageList)
        dbRef = FirebaseDatabase.getInstance().reference
        val name = intent.getStringExtra("NAME")
        val receiverID = intent.getStringExtra("ID")
        val senderID = FirebaseAuth.getInstance().currentUser?.uid

        senderRoom = receiverID + senderID
        receiverRoom = senderID + receiverID

        //set recycler view
        setRecyclerCA()

        //send button
        btnSendCA.setOnClickListener {

            //add message to database
            val message = etInput.text.toString()
            val messageObj = Message(message, senderID!!)

            dbRef.child("chats").child(senderRoom!!).child("messages").push()
                .setValue(messageObj).addOnSuccessListener {
                    dbRef.child("chats").child(receiverRoom!!).child("messages").push()
                        .setValue(messageObj)
                }
        }

    }

    private fun setRecyclerCA() {
        val tempMessage: ArrayList<Message> = ArrayList()

        tempMessage.add(Message("123", "hello there"))

        rvMessageListCA.layoutManager = LinearLayoutManager(this)
    }
}