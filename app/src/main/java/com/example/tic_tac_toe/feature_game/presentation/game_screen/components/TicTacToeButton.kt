package com.example.tic_tac_toe.feature_game.presentation.game_screen.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.sqrt

@Composable
fun TicTacToeButton(
    button: String,
    onClick: () -> Unit,
) {
    Button(onClick = onClick) {
        Text(text = button, fontSize = 10.sp)
    }
}