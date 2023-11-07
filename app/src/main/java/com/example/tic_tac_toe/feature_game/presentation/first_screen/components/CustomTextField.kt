package com.example.tic_tac_toe.feature_game.presentation.first_screen.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import com.example.tic_tac_toe.feature_game.presentation.first_screen.FirstScreenEvent
import com.example.tic_tac_toe.feature_game.presentation.first_screen.FirstScreenViewModel

@Composable
fun CustomTextField(
    playerName: String,
    playerNumber: Int,
    isError: Boolean,
    viewModel: FirstScreenViewModel,
    readOnly: Boolean = false
) {
    TextField(
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text("Jogador $playerNumber") },
        value = playerName,
        readOnly = readOnly,
        isError = isError,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            autoCorrect = false
        ),
        onValueChange = {
            if(playerNumber == 1)
                viewModel.onEvent(FirstScreenEvent.Player1Name(it.filter { char -> char != ' ' }))
            else
                viewModel.onEvent(FirstScreenEvent.Player2Name(it.filter { char -> char != ' ' }))
        }
    )
    ErrorText(isError)
}