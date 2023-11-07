package com.example.tic_tac_toe.feature_game.presentation.first_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.tic_tac_toe.feature_game.domain.use_case.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FirstScreenViewModel @Inject constructor(): ViewModel() {
    private val _isMenuExpanded = mutableStateOf(DropDownMenuState(isExpanded = false))
    val isMenuExpanded: State<DropDownMenuState> = _isMenuExpanded

    private val _menuLabel = mutableStateOf(DropDownMenuState(label = "3x3"))
    val menuLabel: State<DropDownMenuState> = _menuLabel

    private val _gameType = mutableStateOf("vsPlayer")
    val gameType: State<String> = _gameType

//    private val _player1Name = mutableStateOf(TextFieldsState(player1Name = ""))
//    val player1Name: State<TextFieldsState> = _player1Name
//
//    private val _player2Name = mutableStateOf(TextFieldsState(player2Name = ""))
//    val player2Name: State<TextFieldsState> = _player2Name
//
//    private val _isError1 = mutableStateOf(TextFieldsState(isError1 = false))
//    val isError1: State<TextFieldsState> = _isError1
//
//    private val _isError2 = mutableStateOf(TextFieldsState(isError2 = false))
//    val isError2: State<TextFieldsState> = _isError2

    private val _textFieldsState = mutableStateOf(TextFieldsState(
        player1Name = "",
        player2Name = "",
        isError1 = false,
        isError2 = false
    ))

    val textFieldsState: State<TextFieldsState> = _textFieldsState

    fun onEvent(event: FirstScreenEvent) {
        when(event) {
            is FirstScreenEvent.ChangeMenuState -> {
                _isMenuExpanded.value = isMenuExpanded.value.copy(
                    isExpanded = event.state
                )
            }
            is FirstScreenEvent.ChangeMenuLabel -> {
                _menuLabel.value = menuLabel.value.copy(
                    label = event.value
                )
            }
            is FirstScreenEvent.ChangeGameType -> {
                _gameType.value = event.type
            }
            is FirstScreenEvent.Player1Name -> {
                _textFieldsState.value = _textFieldsState.value.copy(
                    player1Name = event.name
                )
            }
            is FirstScreenEvent.Player2Name -> {
                _textFieldsState.value = _textFieldsState.value.copy(
                    player2Name = event.name
                )
            }
            is FirstScreenEvent.ErrorFirstTextField -> {
                _textFieldsState.value = _textFieldsState.value.copy(
                    isError1 = event.state
                )
            }
            is FirstScreenEvent.ErrorSecondTextField -> {
                _textFieldsState.value = _textFieldsState.value.copy(
                    isError2 = event.state
                )
            }
        }
    }
}