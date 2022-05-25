package com.example.tictactoe.Comp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.GridLayout
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.example.tictactoe.R
import kotlinx.android.synthetic.main.activity_versus_comp.*
import kotlin.random.Random

class VersusComp : AppCompatActivity() {
    private val boardcells = Array(3){ arrayOfNulls<ImageView>(3)}
    var board =  Board()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_versus_comp)

//        this board is for UI , we'll map actual board with UI
        loadBoard()

        button_restart.setOnClickListener {
//            new board will be created with empty values
            board = Board()
            text_view_result.text = ""
            mapBoardToUI()
        }
    }

    private fun mapBoardToUI(){
        for (i in boardcells.indices) {
            for (j in boardcells.indices) {
//                check who is player
                when(board.board[i][j]){
                    Board.PLAYER -> {
//                        after turn change image and disable it to click
                        boardcells[i][j]?.setImageResource(R.drawable.circle)
                        boardcells[i][j]?.isEnabled = false
                    }
                    Board.COMPUTER -> {
                        boardcells[i][j]?.setImageResource(R.drawable.cross)
                        boardcells[i][j]?.isEnabled = false
                    }
                    else -> {
//                        empty image
                        boardcells[i][j]?.setImageResource(0)
                        boardcells[i][j]?.isEnabled = true
                    }
                }
            }
        }
    }

    private fun loadBoard(){

        for (i in boardcells.indices) {
            for (j in boardcells.indices){
                boardcells[i][j] = ImageView(this)
                boardcells[i][j]?.layoutParams = GridLayout.LayoutParams().apply {

                    rowSpec = GridLayout.spec(i)
                    columnSpec = GridLayout.spec(j)
                    width = 240
                    height = 220
                    bottomMargin = 4
                    topMargin = 4
                    leftMargin = 4
                    rightMargin=  4
                }
                boardcells[i][j]?.setBackgroundColor(ContextCompat.getColor(this, R.color.black))
                boardcells[i][j]?.setOnClickListener(CellClickListener(i,j))
                layout_board.addView(boardcells[i][j])
            }
        }
    }

//    attach this cell click listener to ImageViews -> inside for loops , loadBoard
    inner class CellClickListener(private val i : Int,
    private val j :Int) : View.OnClickListener{
        override fun onClick(p0: View?) {

            if (!board.isGameOver){
//            get cell first
                val cell = Cell(i,j)
//            it is always player who plays , comp will play automatically
                board.placeMove(cell,Board.PLAYER)

//                when placed a move for player  , this will calculate comp's move
                board.minimax(0, Board.COMPUTER)

//            comp move
                board.computersMove?.let {
                    board.placeMove(it,Board.COMPUTER)
                }

//            map the actual board to UI
                mapBoardToUI()
            }
            when{
                board.hasComputerWon() -> text_view_result.text = "Computer won!"
                board.hasPlayerWon() -> text_view_result.text = "You won!"
                board.isGameOver -> text_view_result.text = "Match Tie!"
            }
        }
    }
}