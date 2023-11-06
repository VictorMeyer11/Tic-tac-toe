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
import com.example.tic_tac_toe.feature_game.presentation.first_screen.FirstScreenEvent

@Composable
fun GameScreen(
    player1Name: String?,
    player2Name: String?,
    boardFormat: String?,
    gameType: String? = "vsBot",
    viewModel: GameViewModel = GameViewModel()
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
                BuildRow(rowId = rowId, viewModel = viewModel, numberOfColumns = numberOfRows, gameType = gameType)
                Spacer(modifier = Modifier.height(10.dp))
            }
        }

        Button(onClick = { viewModel.resetBoard() }){
            Text(text = "Resetar jogo", fontSize = 20.sp)
        }
    }
}

@Composable
fun BuildRow(
    rowId: Int,
    modifier: Modifier = Modifier,
    viewModel: GameViewModel,
    numberOfColumns: Int,
    gameType: String?,
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
                if(gameType == "vsBot") viewModel.botTurn(columnId)
                else viewModel.setButton(columnId)
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