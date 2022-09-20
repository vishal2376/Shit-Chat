package com.vishal.shitchat

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        //animation text
        val txtAnimation = AnimationUtils.loadAnimation(this, R.anim.fade)
        tvAppName.startAnimation(txtAnimation)

        //animation logo
        val logoAnimation = AnimationUtils.loadAnimation(this, R.anim.slide_up)
        imgAppLogo.startAnimation(logoAnimation)

        //check existing sign in user
        val currentUser = FirebaseAuth.getInstance().currentUser

        val runnable = {
            if (currentUser != null) {
                val i = Intent(this, MainActivity::class.java)
                startActivity(i)
                finish()
            }else{
                val i = Intent(this, LoginActivity::class.java)
                startActivity(i)
                finish()
            }
        }

        Handler(Looper.getMainLooper()).postDelayed(
            runnable, 1000
        )

    }
}