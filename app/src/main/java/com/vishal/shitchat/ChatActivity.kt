package com.vishal.shitchat

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue
import com.vishal.shitchat.adapters.MessageAdapter
import com.vishal.shitchat.models.Message
import kotlinx.android.synthetic.main.activity_chat.*

class ChatActivity : AppCompatActivity() {

    lateinit var messageList: ArrayList<Message>
    lateinit var messageAdapter: MessageAdapter

    private lateinit var dbChatRef: DatabaseReference

    private var senderRoom: String? = null
    private var receiverRoom: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        //initialize
        messageList = ArrayList()
        messageAdapter = MessageAdapter(this, messageList)
        dbChatRef = FirebaseDatabase.getInstance().getReference("chats")

        val receiverID = intent.getStringExtra("ID")
        val senderID = FirebaseAuth.getInstance().currentUser?.uid

        senderRoom = receiverID + senderID
        receiverRoom = senderID + receiverID

        //set recycler view
        setRecyclerCA()

        //set layout
        setLayoutCA()

        //load message
        loadMessages()

        //back button
        imgBackCA.setOnClickListener {
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
            finish()
        }

        //send button
        btnSendCA.setOnClickListener {

            //add message to database
            val msg = etInput.text.toString()
            val messageObj = Message(senderID!!, msg)

            dbChatRef.child(senderRoom!!).child("messages").push()
                .setValue(messageObj).addOnSuccessListener {
                    dbChatRef.child(receiverRoom!!).child("messages").push()
                        .setValue(messageObj)
                }
            //reset input box after send msg
            etInput.setText("")
        }

    }

    private fun setLayoutCA() {
        //set name
        val name = intent.getStringExtra("NAME")
        tvNameCA.text = name
    }

    private fun loadMessages() {
        dbChatRef.child(senderRoom!!).child("messages").addValueEventListener(
            object : ValueEventListener {
                @SuppressLint("NotifyDataSetChanged")
                override fun onDataChange(snapshot: DataSnapshot) {

                    messageList.clear()

                    for (dataSnapshot in snapshot.children) {
                        val msg = dataSnapshot.getValue<Message>()
                        messageList.add(msg!!)
                    }

                    messageAdapter.notifyDataSetChanged()
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            }
        )
    }

    private fun setRecyclerCA() {
        rvMessageListCA.layoutManager = LinearLayoutManager(this)
        rvMessageListCA.adapter = messageAdapter
    }
}