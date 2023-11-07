package com.example.tic_tac_toe.feature_game.presentation.history_screen.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MatchItem(
    player1: String?,
    player2: String?,
    winner: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .border(2.dp, MaterialTheme.colors.primary),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text("$player1 vs $player2", fontWeight = FontWeight.Bold, fontSize = 20.sp)
        Text("Vencedor: $winner", fontWeight = FontWeight.Bold, fontSize = 20.sp)
    }
}