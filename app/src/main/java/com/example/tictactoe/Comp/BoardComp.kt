package com.example.tictactoe.Comp

import android.media.MediaPlayer

class Board {
    companion object{
        const val PLAYER = "O"
        const val COMPUTER = "X"
    }
//    in this board our code will work

//     2D array of string , size 3x3
    val board = Array(3){ arrayOfNulls<String>(3)}

    val availableCells : List<Cell>
        get() {
            val cells = mutableListOf<Cell>()
            for (i in board.indices) {
                for (j in board.indices) {
                    if (board[i][j].isNullOrEmpty())
                        cells.add(Cell(i,j))
                }
            }
            return cells
        }

    val isGameOver : Boolean
//    if any condition is true => Game Over
    get()  = hasComputerWon() || hasPlayerWon() || availableCells.isEmpty()
    fun hasComputerWon() : Boolean{
//        checking daignols
        if (board[0][0] == board[1][1] &&
            board[0][0] == board[2][2] &&
            board[0][0] == COMPUTER ||
            board[0][2] == board[1][1] &&
            board[0][2] == board[2][0] &&
            board[0][2] == COMPUTER
         ) {
            return true
        }

        for (i in board.indices){
//            checking every row i ->  0 1 2 and column
            if (
                board[i][0] == board[i][1] &&
                board[i][0] == board[i][2] &&
                board[i][0] == COMPUTER ||
                board[0][i] == board[1][i] &&
                board[0][i] == board[2][i] &&
                board[0][i] == COMPUTER
            ){
                return true
            }
        }

//        if any condition is not returned => comp hasn't won => lost
        return false
    }

    fun hasPlayerWon() : Boolean{
//        checking daignols
        if (board[0][0] == board[1][1] &&
            board[0][0] == board[2][2] &&
            board[0][0] == PLAYER ||
            board[0][2] == board[1][1] &&
            board[0][2] == board[2][0] &&
            board[0][2] == PLAYER
         ) {
            return true
        }

        for (i in board.indices){
//            checking every row i ->  0 1 2 and column
            if (
                board[i][0] == board[i][1] &&
                board[i][0] == board[i][2] &&
                board[i][0] == PLAYER ||
                board[0][i] == board[1][i] &&
                board[0][i] == board[2][i] &&
                board[0][i] == PLAYER
            ){
                return true
            }
        }

        return false
    }


    var computersMove : Cell? = null
    fun minimax(depth : Int,player : String) : Int{
        if (hasComputerWon())return +1
        if (hasPlayerWon())return -1

//        game tie = 0
        if (availableCells.isEmpty()) return 0

//        pre defined constants
        var mIn = Integer.MAX_VALUE
        var mAx = Integer.MIN_VALUE

        for (i in availableCells.indices){
            val cell = availableCells[i]

            if (player == COMPUTER){
                placeMove(cell, COMPUTER)
                val currentscore = minimax(depth+1, PLAYER)

                mAx = Math.max(currentscore,mAx)

                if (currentscore >= 0){
                    if (depth == 0)
                        computersMove = cell
                }
                if (currentscore == 1){
                    board[cell.i][cell.j] = ""
                    break
                }

                if ( i == availableCells.size-1 && mAx < 0){
                    if (depth == 0)
                        computersMove = cell
                }
            }else if(player == PLAYER){
                placeMove(cell, PLAYER)
                val currentscore = minimax(depth+1, COMPUTER)

                mIn = Math.min(currentscore,mIn)

                if (mIn == -1){
                    board[cell.i][cell.j] = ""
//                    break , cause player has already won
                    break
                }
            }
            board[cell.i][cell.j] = ""
        }
        return if (player == COMPUTER) mAx else mIn
    }


    fun placeMove(cell : Cell , player: String){
//        move is placed
        board[cell.i][cell.j] = player
    }

}