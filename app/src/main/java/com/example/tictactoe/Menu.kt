package com.example.tictactoe

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PlayAgainButton(
    modifier: Modifier,
    onResetClick: () -> Unit
){
    Box(modifier = modifier
        .padding(10.dp)
        .background(
            color = Color(43,43,43),
            shape = RoundedCornerShape(15.dp)
        )
        .clickable(onClick = { onResetClick() }),
        contentAlignment = Alignment.Center
    ){
        Text(text = "Play Again",
            color = Color(242, 239, 234),
            fontSize = 30.sp
        )
    }
}

@Composable
fun ResetScoreButton(
    modifier: Modifier,
    onResetScoreClick: () -> Unit
){
    Box(modifier = modifier
        .padding(10.dp)
        .background(
            color = Color(43,43,43),
            shape = RoundedCornerShape(15.dp)
        )
        .clickable(onClick = { onResetScoreClick() }),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Reset Score",
            color = Color(242, 239, 234),
            fontSize = 30.sp
        )
    }
}