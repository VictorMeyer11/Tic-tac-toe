package com.example.tic_tac_toe.feature_game.presentation.util

sealed class Screen(val route: String) {
    object FirstScreen: Screen("first_screen")
    object GameScreen: Screen("game_screen/{player1_name}/{player2_name}/{board_format}")
}