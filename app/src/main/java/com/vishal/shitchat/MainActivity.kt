package com.vishal.shitchat

import android.os.Bundle
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.vishal.shitchat.adapters.UserAdapter
import com.vishal.shitchat.models.User
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //set recycler view
        setRecyclerMA()

    }

    private fun setRecyclerMA() {

        //temp data for testing
        val tempData: ArrayList<User> = ArrayList()
        tempData.add(User("123","Manik","", "Join the world's best online", "08:10 PM", ""))
        tempData.add(User("123","Anshal","", "Learn about basic Data Structure", "10:20 PM", ""))
        tempData.add(User("123","Unknown","", "Build products, practice skills", "08:40 PM", ""))
        tempData.add(User("123","Bhatia","", "and in-person hackathons", "01:20 AM", ""))
        tempData.add(User("123","Manik", "","Showing 7304 hackathons", "03:13 PM", ""))
        tempData.add(User("123","Mani", "","just use pan and paper", "04:10 AM", ""))
        tempData.add(User("123","Anshal", "","Learn about basic Data Structure", "10:20 PM", ""))
        tempData.add(User("123","Unknown","" ,"Build products, practice skills", "08:40 PM", ""))
        tempData.add(User("123","Bhatia", "","and in-person hackathons", "01:20 AM", ""))
        tempData.add(User("123","Unknown","" ,"Build products, practice skills", "08:40 PM", ""))
        tempData.add(User("123","Bhatia","","and in-person hackathons", "01:20 AM", ""))
        tempData.add(User("123","Manik", "","Showing 7304 hackathons", "03:13 PM", ""))
        tempData.add(User("123","Mani", "","just use pan and paper", "04:10 AM", ""))
        tempData.add(User("123","Anshal", "","Learn about basic Data Structure", "10:20 PM", ""))


        rvChatList.layoutManager = LinearLayoutManager(this)
        rvChatList.adapter = UserAdapter(this, tempData)

        //layout animation added
        val chatListAnimation = LayoutAnimationController(AnimationUtils.loadAnimation(this,R.anim.slide_up))
        chatListAnimation.delay = 0.06f
        chatListAnimation.order = LayoutAnimationController.ORDER_NORMAL
        rvChatList.layoutAnimation = chatListAnimation
    }
}