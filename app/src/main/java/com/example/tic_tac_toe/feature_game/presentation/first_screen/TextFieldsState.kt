package com.example.tic_tac_toe.feature_game.presentation.first_screen

data class TextFieldsState(
    val player1Name: String = "",
    val player2Name: String = "",
    val isError1: Boolean = false,
    val isError2: Boolean = false
)