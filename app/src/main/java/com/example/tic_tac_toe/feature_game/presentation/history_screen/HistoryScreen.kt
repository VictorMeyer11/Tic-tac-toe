package com.example.tic_tac_toe.feature_game.presentation.history_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tic_tac_toe.feature_game.presentation.history_screen.components.MatchItem

@Composable
fun HistoryScreen(
    viewModel: HistoryScreenViewModel = hiltViewModel()
) {
    val matchHistory = viewModel.matchHistory.value

    if(matchHistory.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("Não há partidas registradas", fontWeight = FontWeight.Black)
        }
    }

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(matchHistory) { match ->
            MatchItem(match.player1, match.player2, match.winner)
            Spacer(Modifier.height(5.dp))
        }
    }
}