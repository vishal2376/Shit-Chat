package com.vishal.shitchat

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue
import com.vishal.shitchat.adapters.UserAdapter
import com.vishal.shitchat.models.User
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var dbUserRef: DatabaseReference

    lateinit var userList: ArrayList<User>
    lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //initialize
        auth = FirebaseAuth.getInstance()
        dbUserRef = FirebaseDatabase.getInstance().getReference("user")

        userList = ArrayList()
        userAdapter = UserAdapter(this, userList)

        //set recycler view
        setRecyclerMA()

        //load database
        loadDatabase()


        //logout button
        btnLogoutMA.setOnClickListener {
            logout()
        }

    }

    private fun loadDatabase() {
        dbUserRef.addValueEventListener(object : ValueEventListener {

            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {

                userList.clear()

                for (postSnapshot in snapshot.children) {
                    val currentUser = postSnapshot.getValue<User>()
                    userList.add(currentUser!!)
                }

                //update recycler view data
                userAdapter.notifyDataSetChanged()
            }


            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    //logout user and move to login screen
    private fun logout() {
        auth.signOut()

        val i = Intent(this, LoginActivity::class.java)
        startActivity(i)
        finish()
    }

    private fun setRecyclerMA() {

        //set layout and adapter
        rvChatList.layoutManager = LinearLayoutManager(this)
        rvChatList.adapter = userAdapter

        //layout animation added
        val userListAnimation =
            LayoutAnimationController(AnimationUtils.loadAnimation(this, R.anim.slide_up))
        userListAnimation.delay = 0.06f
        userListAnimation.order = LayoutAnimationController.ORDER_NORMAL
        rvChatList.layoutAnimation = userListAnimation
    }
}