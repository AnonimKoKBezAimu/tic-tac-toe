package com.example.tictactoe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.tictactoe.ui.theme.TicTacToeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var gridSize by remember {mutableStateOf(3)}
            var playerTurn by remember { mutableStateOf("X") }
            var winCount by remember { if (gridSize < 5){mutableStateOf(3)}
                                       else {mutableStateOf(5)}
                                     }
            var playerXScore: Int by remember { mutableStateOf(0) }
            var playerYScore: Int by remember { mutableStateOf(0) }
            var gameState: GameState by remember { mutableStateOf(GameState.Playing) }

            val board = remember(gridSize) {
                mutableStateListOf<SnapshotStateList<Cell>>().apply {
                    repeat(gridSize) {
                        add(
                            mutableStateListOf<Cell>().apply {
                                repeat(gridSize) {
                                    add(Cell.EMPTY)
                                }
                            }
                        )
                    }
                }
            }

            Column(modifier = Modifier.fillMaxSize()
                .background(color = Color(24, 75, 46))
            ) {
                ScoreBoard(Modifier.weight(1f),
                    playerXScore,
                    playerYScore,
                    playerTurn
                )

                Grid(Modifier.weight(4f),
                    gridSize,
                    board) {
                    i, j ->

                    if (checkWin(board, gridSize, playerTurn, winCount, i, j)){
                        when (playerTurn){
                            "X" -> {
                                if (board[i][j] == Cell.EMPTY && gameState == GameState.Playing){
                                    playerXScore++
                                    board[i][j] = Cell.X
                                    playerTurn = "O"
                                    }
                            }
                            "O" -> {
                                if (board[i][j] == Cell.EMPTY && gameState == GameState.Playing){
                                    playerYScore++
                                    board[i][j] = Cell.O
                                    playerTurn = "X"
                                }
                            }
                        }
                        gameState = GameState.GameOver
                    }
                    else if (board[i][j] == Cell.EMPTY && gameState == GameState.Playing) {
                        if (playerTurn == "X") {
                            board[i][j] = Cell.X
                            playerTurn = "O"
                        } else if (playerTurn == "O") {
                            board[i][j] = Cell.O
                            playerTurn = "X"
                        }
                    }

                }
                Row(modifier = Modifier.weight(1f),
                    horizontalArrangement = Arrangement.SpaceEvenly) {
                    PlayAgainButton(
                        Modifier.fillMaxSize()
                            .weight(1f)
                    ) {
                        for (i in 0 until gridSize) {
                            for (j in 0 until gridSize) {
                                board[i][j] = Cell.EMPTY
                            }
                        }
                        gameState = GameState.Playing
                    }
                    ResetScoreButton(
                        Modifier.fillMaxSize()
                            .weight(1f)
                    ) {
                        playerXScore = 0
                        playerYScore = 0
                        playerTurn = "X"

                        for (i in 0 until gridSize) {
                            for (j in 0 until gridSize) {
                                board[i][j] = Cell.EMPTY
                            }
                        }
                        gameState = GameState.Playing
                    }
                }
            }
        }
    }
}

enum class Cell { EMPTY, X, O }
enum class GameState {Playing, GameOver}