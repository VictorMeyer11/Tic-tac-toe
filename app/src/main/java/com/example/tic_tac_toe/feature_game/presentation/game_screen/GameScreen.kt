package com.example.tic_tac_toe.feature_game.presentation.game_screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GameScreen(
    modifier: Modifier = Modifier,
    viewModel: GameViewModel = GameViewModel(),
    numberOfRows: Int = 5,
){
    val state = viewModel.state.value

    if(viewModel.state.value.buttonWinners.isEmpty()) viewModel.setBoardSize(numberOfRows*numberOfRows)

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val turn = if(state.isPlayer1sTurn) "X's Turn" else "O's Turn"
        val turnMessage = "Tic Tac Toe\nIt is $turn"
        val winner = state.victor
        val winnerMessage = "Tic Tac Toe\n$winner Wins"

        Text(
            text = if(winner != null) winnerMessage else turnMessage,
            textAlign = TextAlign.Center,
            modifier = modifier.padding(16.dp),
            fontSize = 40.sp
        )
        Column {
            for(rowId in 1..numberOfRows) {
                BuildRow(rowId = rowId, viewModel = viewModel, numberOfColumns = numberOfRows)
                Spacer(modifier = Modifier.height(10.dp))
            }
        }

        Button(onClick = { viewModel.resetBoard() }){
            Text(text = "Reset Game", fontSize = 32.sp)
        }
    }
}

@Composable
fun BuildRow(
    rowId: Int,
    modifier: Modifier = Modifier,
    viewModel: GameViewModel,
    numberOfColumns: Int
){
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth()
    ) {
        val first = rowId*numberOfColumns - numberOfColumns
        val last = rowId*numberOfColumns
        val buttonColors = viewModel.state.value.buttonWinners
        val buttonValues = viewModel.state.value.buttonValues

        for(columnId in first until last) {
            TicTacToeButton(buttonValues[columnId], buttonColors[columnId]) {
                viewModel.setButton(columnId)
            }
        }
//        val third = (rowId * 3) - 1
//        val second = third - 1
//        val first = second - 1
//
//
//
//        TicTacToeButton(buttonValues[second], buttonColors[second]) { viewModel.setButton(second)}
//        TicTacToeButton(buttonValues[third], buttonColors[third]) { viewModel.setButton(third)}
    }
}

@Composable
fun TicTacToeButton(
    button: String,
    shouldChangeColor: Boolean,
    onClick: () -> Unit,
) {
    val color = if(shouldChangeColor) Color.LightGray
    else Color.White

    Button(
        onClick = onClick,
        modifier = Modifier.defaultMinSize(10.dp)
    ) {
        Text(text = button, fontSize = 10.sp)
    }
}