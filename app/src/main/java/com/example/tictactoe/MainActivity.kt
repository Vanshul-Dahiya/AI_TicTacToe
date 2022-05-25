package com.example.tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        iv_tic.translationY = -1000f
        tv_tic.translationY = 1000f

//      back to it's original place
        iv_tic.animate().translationY(0f).duration = 1200
        tv_tic.animate().translationY(0f).duration = 1200

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this,StartActivity::class.java)
            startActivity(intent)
            finish()
        },1900)
    }
}