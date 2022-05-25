package com.example.tictactoe

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_game.*
import kotlinx.android.synthetic.main.winner_dailog.*

class GameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

    }
    fun BtnClick(view : View){

        var cellID = 0
        val buttonSelected = view as Button

        when(buttonSelected.id){
            R.id.button1 -> {
                cellID = 1
            }
            R.id.button2 -> {
                cellID = 2
            }
            R.id.button3 -> {
                cellID = 3
            }
            R.id.button4 -> {
                cellID = 4
            }
            R.id.button5 -> {
                cellID = 5
            }
            R.id.button6 -> {
                cellID = 6
            }
            R.id.button7 -> {
                cellID = 7
            }
            R.id.button8 -> {
                cellID = 8
            }
            R.id.button9 -> {
                cellID =9
            }
        }
        playGame(cellID , buttonSelected)
    }

    var activePlayer = 1
    var player1 = ArrayList<Int>()
    var player2 = ArrayList<Int>()
    var DrawArray = ArrayList<Int>()

    fun playGame(cellId : Int, btnSelected : Button){
        if (activePlayer==1){
            btnSelected.text = "X"
            btnSelected.setBackgroundResource(R.drawable.button_bg)
//            so that , button can't be clicked again  -> can't be used
            player1.add(cellId)
            DrawArray.add(cellId)
            activePlayer = 2
        }else{
            btnSelected.text = "0"
            btnSelected.setBackgroundResource(R.drawable.button_bg2)
            player2.add(cellId)
            DrawArray.add(cellId)
            activePlayer = 1
        }
        btnSelected.isEnabled = false

        checkWinner()
    }

    fun checkWinner(){
        var winner = -1

//        row 1 winner
        if (player1.contains(1) && player1.contains(2) && player1.contains(3))
            winner = 1

        if (player2.contains(1) && player2.contains(2) && player2.contains(3))
            winner = 2

//       row 2 winner
        if (player1.contains(4) && player1.contains(5) && player1.contains(6))
            winner = 1

        if (player2.contains(4) && player2.contains(5) && player2.contains(6))
            winner = 2

//        row 3 winner
        if (player1.contains(7) && player1.contains(8) && player1.contains(9))
            winner = 1

        if (player2.contains(7) && player2.contains(8) && player2.contains(9))
            winner = 2


//        col 1 winner
        if (player1.contains(1) && player1.contains(4) && player1.contains(7))
            winner = 1

        if (player2.contains(1) && player2.contains(4) && player2.contains(7))
            winner = 2

//        col 2 winner
        if (player1.contains(2) && player1.contains(5) && player1.contains(8))
            winner = 1

        if (player2.contains(2) && player2.contains(5) && player2.contains(8))
            winner = 2

//        col 3 winner
        if (player1.contains(3) && player1.contains(6) && player1.contains(9))
            winner = 1

        if (player2.contains(3) && player2.contains(6) && player2.contains(9))
            winner = 2

//        daignol 1 winner
        if (player1.contains(1) && player1.contains(5) && player1.contains(9))
            winner = 1

        if (player2.contains(1) && player2.contains(5) && player2.contains(9))
            winner = 2

//        daignol 1 winner
        if (player1.contains(3) && player1.contains(5) && player1.contains(7))
            winner = 1

        if (player2.contains(3) && player2.contains(5) && player2.contains(7))
            winner = 2


        if (winner == 1){
            Toast.makeText(this,"Player 1 wins ",Toast.LENGTH_SHORT).show()

            val alertDialog = Dialog(this)
            alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            alertDialog.setCancelable(false)
            alertDialog.setContentView(R.layout.winner_dailog)
            alertDialog.winner.text = "Player 1 WON !!"
            alertDialog.exit.setOnClickListener{
                startActivity(Intent(this,StartActivity::class.java))
            }
            alertDialog.play_again.setOnClickListener {
                val intent = Intent(this,GameActivity::class.java)
                finish()
                startActivity(intent)
            }
            alertDialog.show()
        }
        else if(winner == 2){
            Toast.makeText(this,"Player 2 wins",Toast.LENGTH_SHORT).show()

            val alertDialog = Dialog(this)
            alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            alertDialog.setCancelable(false)
            alertDialog.setContentView(R.layout.winner_dailog)
            alertDialog.winner.text = "Player 2 WON !!"
            alertDialog.exit.setOnClickListener{
                startActivity(Intent(this,StartActivity::class.java))
            }
            alertDialog.play_again.setOnClickListener {
                val intent = Intent(this,GameActivity::class.java)
                finish()
                startActivity(intent)
            }
            alertDialog.show()
        }
        else if(winner == -1 && DrawArray.contains(1) && DrawArray.contains(2) && DrawArray.contains(3)
            && DrawArray.contains(4) && DrawArray.contains(5) && DrawArray.contains(6)
            && DrawArray.contains(7) && DrawArray.contains(8) && DrawArray.contains(9)){
            val alertDialog = Dialog(this)
            alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            alertDialog.setCancelable(false)
            alertDialog.setContentView(R.layout.winner_dailog)
            alertDialog.winner.text = "Good Game , Try hard next time!"
            alertDialog.exit.setOnClickListener{
                startActivity(Intent(this,StartActivity::class.java))
            }
            alertDialog.play_again.setOnClickListener {
                val intent = Intent(this,GameActivity::class.java)
                finish()
                startActivity(intent)
            }
            alertDialog.show()
        }
    }

}