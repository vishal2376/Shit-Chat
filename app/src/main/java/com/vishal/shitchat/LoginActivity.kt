package com.vishal.shitchat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var auth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //initialize
        auth = FirebaseAuth.getInstance()

        //login button
        btnLogin.setOnClickListener {
            val email = etEmailLA.text.toString()
            val password = etPasswordLA.text.toString()

            //login with email and password
            login(email,password)

        }

        //google login button
        btnGoogleLogin.setOnClickListener {
            Toast.makeText(this,"No implemented",Toast.LENGTH_SHORT).show()
        }

        //sign up page button
        tvSignUpLA.setOnClickListener {
            val i = Intent(this,SignUpActivity::class.java)
            startActivity(i)
            finish()
        }
    }

    private fun login(email: String, password: String) {

    }
}