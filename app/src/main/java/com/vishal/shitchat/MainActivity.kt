package com.vishal.shitchat

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.vishal.shitchat.adapters.ChatAdapter
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
        val tempData: ArrayList<Person> = ArrayList()
        tempData.add(Person("Manik", "Join the world's best online", "08:10 PM", ""))
        tempData.add(Person("Anshal", "Learn about basic Data Structure", "10:20 PM", ""))
        tempData.add(Person("Tarkwadi", "Build products, practice skills", "08:40 PM", ""))
        tempData.add(Person("Bhatia", "and in-person hackathons", "01:20 AM", ""))
        tempData.add(Person("Manik", "Showing 7304 hackathons", "03:13 PM", ""))
        tempData.add(Person("Hareta", "just use pan and paper", "04:10 AM", ""))
        tempData.add(Person("Anshal", "Learn about basic Data Structure", "10:20 PM", ""))
        tempData.add(Person("Tarkwadi", "Build products, practice skills", "08:40 PM", ""))
        tempData.add(Person("Bhatia", "and in-person hackathons", "01:20 AM", ""))
        tempData.add(Person("Tarkwadi", "Build products, practice skills", "08:40 PM", ""))
        tempData.add(Person("Bhatia", "and in-person hackathons", "01:20 AM", ""))
        tempData.add(Person("Manik", "Showing 7304 hackathons", "03:13 PM", ""))
        tempData.add(Person("Hareta", "just use pan and paper", "04:10 AM", ""))
        tempData.add(Person("Anshal", "Learn about basic Data Structure", "10:20 PM", ""))


        rvChatList.layoutManager = LinearLayoutManager(this)
        rvChatList.adapter = ChatAdapter(this, tempData)
    }
}