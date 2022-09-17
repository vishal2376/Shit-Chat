package com.vishal.shitchat

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.vishal.shitchat.models.User
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)


        //initialize
        auth = FirebaseAuth.getInstance()

        //sign up button
        btnSignUp.setOnClickListener {
            val name = etNameSA.text.toString()
            val email = etEmailSA.text.toString()
            val password = etPasswordSA.text.toString()

            //login with email and password
            signUp(name, email, password)
        }

        //login page button
        tvLoginSA.setOnClickListener {
            val i = Intent(this, LoginActivity::class.java)
            startActivity(i)
            finish()
        }
    }

    private fun signUp(name: String, email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                    //add user to chat database
                    addUserToDatabase(auth.uid!!,name,email)

                    //move to chat room
                    val i = Intent(this, MainActivity::class.java)
                    startActivity(i)
                } else {
                    Toast.makeText(
                        baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    private fun addUserToDatabase(uid: String, name: String, email: String) {
        dbRef = FirebaseDatabase.getInstance().reference
        val tempUser = User(uid,name,email,"","","")

        //creating user data
        dbRef.child("user").child(uid).setValue(tempUser)
    }
}