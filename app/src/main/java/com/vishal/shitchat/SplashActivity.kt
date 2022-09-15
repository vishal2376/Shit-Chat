package com.vishal.shitchat

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
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

        Handler(Looper.getMainLooper()).postDelayed(
            {
                val i = Intent(this, MainActivity::class.java)
                startActivity(i)
                finish()
            }, 1000
        )

    }
}