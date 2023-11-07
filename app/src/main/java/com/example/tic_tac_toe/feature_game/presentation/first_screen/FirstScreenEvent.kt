package com.example.tic_tac_toe.feature_game.presentation.first_screen

sealed class FirstScreenEvent {
    data class ChangeMenuState(val state: Boolean): FirstScreenEvent()
    data class ChangeMenuLabel(val value: String): FirstScreenEvent()
    data class ChangeGameType(val type: String): FirstScreenEvent()
    data class Player1Name(val name: String): FirstScreenEvent()
    data class Player2Name(val name: String): FirstScreenEvent()
    data class ErrorFirstTextField(val state: Boolean): FirstScreenEvent()
    data class ErrorSecondTextField(val state: Boolean): FirstScreenEvent()
}