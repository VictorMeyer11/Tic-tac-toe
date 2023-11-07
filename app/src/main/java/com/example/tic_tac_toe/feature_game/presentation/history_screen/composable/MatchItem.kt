package com.example.tic_tac_toe.feature_game.presentation.history_screen.composable

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MatchItem(
    player1: String?,
    player2: String?,
    winner: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .border(2.dp, Color.Gray),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("$player1 vs $player2")
        Spacer(Modifier.height(15.dp))
        Text("Vencedor: $winner")
    }
}