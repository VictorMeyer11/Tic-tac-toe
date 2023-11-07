package com.example.tic_tac_toe.feature_game.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tic_tac_toe.feature_game.presentation.first_screen.FirstScreen
import com.example.tic_tac_toe.feature_game.presentation.game_screen.GameScreen
import com.example.tic_tac_toe.feature_game.presentation.history_screen.HistoryScreen
import com.example.tic_tac_toe.feature_game.presentation.util.Screen
import com.example.tic_tac_toe.ui.theme.TictactoeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TictactoeTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = Screen.FirstScreen.route
                    ) {
                        composable(route = Screen.FirstScreen.route) {
                            FirstScreen(navController = navController)
                        }
                        composable(
                            route = Screen.GameScreen.route,
                            arguments = listOf(
                                navArgument("player1_name") {
                                    type = NavType.StringType
                                },
                                navArgument("player2_name") {
                                    type = NavType.StringType
                                },
                                navArgument("board_format") {
                                    type = NavType.StringType
                                }
                            )
                        ) {
                            it.arguments?.let { bundle ->
                                GameScreen(
                                    bundle.getString("player1_name"),
                                    bundle.getString("player2_name"),
                                    bundle.getString("board_format"),
                                    bundle.getString("game_type")
                                )
                            }
                        }
                        composable(route = Screen.HistoryScreen.route) {
                            HistoryScreen()
                        }
                    }
                }
            }
        }
    }
}