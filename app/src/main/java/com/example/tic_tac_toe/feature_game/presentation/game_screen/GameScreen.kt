package com.example.tic_tac_toe.feature_game.presentation.game_screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tic_tac_toe.feature_game.presentation.game_screen.components.TicTacToeButton

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GameScreen(
    player1Name: String?,
    player2Name: String?,
    boardFormat: String?,
    gameType: String?,
    viewModel: GameViewModel = hiltViewModel()
){
    val state = viewModel.state.value
    val numberOfRows = boardFormat?.substringAfterLast('x')?.toInt() ?: 0
    if(viewModel.state.value.buttonWinners.isEmpty()) viewModel.setBoardSize(numberOfRows*numberOfRows)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val turn = if(state.isPlayer1sTurn) "$player1Name's Turn" else "$player2Name's Turn"
        val turnMessage = "Tic Tac Toe\nIt is $turn"
        val winner = when (state.victor) {
                     "X" -> player1Name
                     "O" -> player2Name
                     else -> null
        }
        val winnerMessage = "Tic Tac Toe\n$winner Wins"

        Text(
            text = if(winner != null) winnerMessage else turnMessage,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(16.dp),
            fontSize = 40.sp
        )
        LazyVerticalGrid(cells = GridCells.Fixed(numberOfRows)) {
            itemsIndexed(viewModel.state.value.buttonValues) { index, value ->
                Box(
                    modifier = Modifier.padding(vertical = 4.dp, horizontal = 2.dp),
                    contentAlignment = Alignment.Center
                ) {
                    TicTacToeButton(value) {
                        viewModel.setButton(index, gameType, player1Name, player2Name)
                    }
                }
            }
        }

        Button(
            onClick = viewModel::resetBoard,
            modifier = Modifier.fillMaxWidth()
        ){
            Text(text = "Resetar jogo", fontSize = 24.sp)
        }
    }
}