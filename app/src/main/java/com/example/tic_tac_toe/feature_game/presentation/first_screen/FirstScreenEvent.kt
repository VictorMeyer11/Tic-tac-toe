package com.example.tic_tac_toe.feature_game.presentation.first_screen

sealed class FirstScreenEvent {
    data class ChangeMenuState(val state: Boolean): FirstScreenEvent()
    data class ChangeMenuLabel(val value: String): FirstScreenEvent()
}