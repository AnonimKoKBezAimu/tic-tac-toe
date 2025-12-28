package com.example.tictactoe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.tooling.preview.Preview
import com.example.tictactoe.ui.theme.TicTacToeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var gridSize by remember {mutableStateOf(3)}
            var playerTurn by remember { mutableStateOf("X") }

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
            ) {
                Grid(Modifier.weight(4f),
                    gridSize,
                    board) {
                    i, j ->

                    if (board[i][j] == Cell.EMPTY) {
                        if (playerTurn == "X") {
                            board[i][j] = Cell.X
                            playerTurn = "O"
                        } else if (playerTurn == "O") {
                            board[i][j] = Cell.O
                            playerTurn = "X"
                        }
                    }
                }
                ResetButton(Modifier.fillMaxSize()
                            .weight(1f)
                ) {
                    for(i in 0 until gridSize){
                        for(j in 0 until gridSize){
                            board[i][j] = Cell.EMPTY
                        }
                    }
                }
            }
        }
    }
}

enum class Cell { EMPTY, X, O }