package com.example.tic_tac_toe.feature_game.presentation.game_screen.components

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun TicTacToeButton(
    button: String,
    onClick: () -> Unit,
) {
    Button(onClick = onClick) {
        Text(text = button)
    }
}