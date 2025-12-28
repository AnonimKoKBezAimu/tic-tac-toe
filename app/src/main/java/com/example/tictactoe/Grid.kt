package com.example.tictactoe

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment

@Composable
fun Grid(
    modifier: Modifier,
    gridSize: Int,
    board: List<List<Cell>>,
    onCellClick: (Int, Int) -> Unit
){
    Column(modifier = modifier
        .fillMaxHeight()
    ) {
        for(i in 0..gridSize -1){

            Row(modifier = modifier
                .weight(1f)
                .fillMaxWidth()
            ) {
                for(j in 0..gridSize -1){

                    Box(modifier = modifier
                        .weight(1f)
                        .fillMaxSize()
                        .border(1.dp, color = Color.Black)
                        .clickable() {
                            onCellClick(i, j)
                        },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = when (board[i][j]){
                            Cell.X -> "X"
                            Cell.O -> "O"
                            Cell.EMPTY -> "empty"
                        }
                        )
                    }
                }
            }
        }
    }
}