package com.example.tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.tictactoe.Comp.VersusComp
import kotlinx.android.synthetic.main.activity_start.*

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        startBtn.setOnClickListener{
            startActivity(Intent(this,GameActivity::class.java))
        }
        versusComp.setOnClickListener{
            startActivity(Intent(this, VersusComp::class.java))
        }

    }
}