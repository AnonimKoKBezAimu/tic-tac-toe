package com.example.tictactoe

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

fun checkWin(
    board: List<List<Cell>>,
    gridSize: Int,
    playerTurn: String,
    winCount: Int,
    i: Int,
    j: Int,
): Boolean{
    val playerCell: Cell = if (playerTurn == "X") Cell.X else Cell.O

    val directions = listOf(
        Pair(1, 0),  // |
        Pair(0, 1),  // -
        Pair(1, 1),  // \
        Pair(1, -1), // /
    )

    for ((directionX, directionY) in directions){
        var currentCellCount: Int = 1

        var x = i + directionX
        var y = j + directionY
        while ((x >= 0 && x < gridSize) && (y >= 0 && y < gridSize) && board[x][y] == playerCell){
            currentCellCount++
            x += directionX
            y += directionY
        }

        x = i - directionX
        y = j - directionY
        while ((x >= 0 && x < gridSize) && (y >= 0 && y < gridSize) && board[x][y] == playerCell){
            currentCellCount++
            x -= directionX
            y -= directionY
        }

        if (currentCellCount >= winCount){
            return true
        }
    }
    return false
}

@Composable
fun ScoreBoard(
    modifier: Modifier,
    playerXScore: Int,
    playerYScore: Int,
    playerTurn: String
){
    Row(modifier = modifier.fillMaxSize()
        .padding(10.dp)
        .background(
            color = Color(43,43,43),
            shape = RoundedCornerShape(15.dp)
        ),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(text = "X", color = Color(58, 124, 165), fontSize = 100.sp)
            Text(text = "-", color = Color(242, 239, 234), fontSize = 100.sp)
            Text(text = playerXScore.toString(), color = Color(242, 239, 234), fontSize = 100.sp)
        }
        Row(Modifier.weight(0.2f),
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            if (playerTurn == "X") {
                Text(text = "X", color = Color(58, 124, 165), fontSize = 40.sp)
            }
            else if (playerTurn == "O") {
                Text(text = "O", color = Color(179, 58, 58), fontSize = 40.sp)
            }
        }
        Row (Modifier.weight(1f),
            horizontalArrangement = Arrangement.SpaceEvenly
            ){
            Text(text = "O", color = Color(179, 58, 58), fontSize = 100.sp)
            Text(text = "-", color = Color(242, 239, 234), fontSize = 100.sp)
            Text(text = playerYScore.toString(), color = Color(242, 239, 234), fontSize = 100.sp)
        }
    }
}
