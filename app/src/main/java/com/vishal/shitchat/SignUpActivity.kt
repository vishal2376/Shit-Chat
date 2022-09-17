package com.vishal.shitchat

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

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

    }
}