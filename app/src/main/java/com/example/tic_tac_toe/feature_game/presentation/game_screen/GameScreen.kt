package com.example.tic_tac_toe.feature_game.presentation.game_screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tic_tac_toe.feature_game.presentation.game_screen.components.BuildRow

@Composable
fun GameScreen(
    player1Name: String?,
    player2Name: String?,
    boardFormat: String?,
    gameType: String?,
    viewModel: GameViewModel = hiltViewModel()
){
    val state = viewModel.state.value
    val numberOfRows = boardFormat?.first()?.toString()?.toInt()!!
    if(viewModel.state.value.buttonWinners.isEmpty()) viewModel.setBoardSize(numberOfRows*numberOfRows)

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val turn = if(state.isPlayer1sTurn) "$player1Name's Turn" else "$player2Name's Turn"
        val turnMessage = "Tic Tac Toe\nIt is $turn"
        val winner = if(state.victor == "X") player1Name
                     else if(state.victor == "O") player2Name
                     else null
        val winnerMessage = "Tic Tac Toe\n$winner Wins"

        Text(
            text = if(winner != null) winnerMessage else turnMessage,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(16.dp),
            fontSize = 40.sp
        )
        Column {
            for(rowId in 1..numberOfRows) {
                BuildRow(
                    rowId = rowId,
                    viewModel = viewModel,
                    numberOfColumns = numberOfRows,
                    gameType = gameType,
                    player1Name = player1Name,
                    player2Name = player2Name
                )
                Spacer(modifier = Modifier.height(10.dp))
            }
        }

        Button(onClick = { viewModel.resetBoard() }){
            Text(text = "Resetar jogo", fontSize = 20.sp)
        }
    }
}