package com.vishal.shitchat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //login button
        btnLogin.setOnClickListener {
            val i = Intent(this,MainActivity::class.java)
            startActivity(i)
            finish()
        }

        //google login button
        btnGoogleLogin.setOnClickListener {
            Toast.makeText(this,"No implemented",Toast.LENGTH_SHORT).show()
        }

        //sign up button
        tvSignUp.setOnClickListener {
            val i = Intent(this,SignUpActivity::class.java)
            startActivity(i)
            finish()
        }
    }
}