package com.example.tic_tac_toe.feature_game.presentation.game_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.tic_tac_toe.feature_game.presentation.game_screen.GameViewModel

@Composable
fun BuildRow(
    rowId: Int,
    modifier: Modifier = Modifier,
    viewModel: GameViewModel,
    numberOfColumns: Int,
    gameType: String?,
    player1Name: String?,
    player2Name: String?,
){
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth()
    ) {
        val first = rowId*numberOfColumns - numberOfColumns
        val last = rowId*numberOfColumns
        val buttonValues = viewModel.state.value.buttonValues

        for(columnId in first until last) {
            TicTacToeButton(buttonValues[columnId]) {
                viewModel.setButton(columnId, gameType, player1Name, player2Name)
            }
        }
    }
}