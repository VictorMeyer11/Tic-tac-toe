package com.example.tic_tac_toe.feature_game.presentation.first_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.tic_tac_toe.feature_game.presentation.first_screen.components.CustomTextField
import com.example.tic_tac_toe.feature_game.presentation.first_screen.components.ErrorText
import com.example.tic_tac_toe.feature_game.presentation.util.Screen

@Composable
fun FirstScreen(
    navController: NavController,
    viewModel: FirstScreenViewModel = hiltViewModel()
) {
    val smallSpace = 16.dp
    val greatSpace = 32.dp
    val fontSize = 24.sp

    val listOfOptions = listOf(
        "3x3", "4x4", "5x5", "6x6", "7x7", "8x8", "9x9", "10x10"
    )

    val textFiledSize = remember {
        mutableStateOf(Size.Zero)
    }

    val gameType = viewModel.gameType.value
    val player1Name = viewModel.textFieldsState.value.player1Name
    val player2Name = if(gameType == "vsBot") "Robô"
                      else viewModel.textFieldsState.value.player2Name

    val isMenuExpanded = viewModel.isMenuExpanded.value.isExpanded
    val dropDownMenuLabel = viewModel.menuLabel.value.label
    val isError1 = viewModel.textFieldsState.value.isError1
    val isError2 = viewModel.textFieldsState.value.isError2

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = smallSpace),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(Modifier.height(greatSpace))
        Text("Tipo de jogo", fontSize = fontSize)
        Spacer(Modifier.height(smallSpace))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                colors = if(gameType == "vsBot") ButtonDefaults.buttonColors(backgroundColor = Color.LightGray)
                         else ButtonDefaults.buttonColors(backgroundColor = Color.White),
                modifier = Modifier.fillMaxWidth(.5f),
                shape = RectangleShape,
                onClick = { viewModel.onEvent(FirstScreenEvent.ChangeGameType("vsPlayer")) }
            ) {
                Text("vs Jogador", color = Color.DarkGray)
            }
            Button(
                colors = if(gameType == "vsPlayer") ButtonDefaults.buttonColors(backgroundColor = Color.LightGray)
                         else ButtonDefaults.buttonColors(backgroundColor = Color.White),
                modifier = Modifier.fillMaxWidth(),
                shape = RectangleShape,
                onClick = { viewModel.onEvent(FirstScreenEvent.ChangeGameType("vsBot")) }
            ) {
                Text("vs Bot", color = Color.DarkGray)
            }
        }
        Spacer(Modifier.height(smallSpace))
        Text("Nome dos jogadores", fontSize = fontSize)
        Spacer(Modifier.height(smallSpace))
        CustomTextField(player1Name,1, isError1, viewModel)
        Spacer(Modifier.height(smallSpace))
        CustomTextField(player2Name,2, isError2, viewModel, gameType=="vsBot")
        Spacer(Modifier.height(smallSpace))
        Text("Tamanho do tabuleiro", fontSize = fontSize)
        Spacer(Modifier.height(smallSpace))
        OutlinedTextField(
            modifier = Modifier.onGloballyPositioned { coordinates ->
                textFiledSize.value = coordinates.size.toSize()
            },
            readOnly = true,
            onValueChange = {},
            value = dropDownMenuLabel,
            label = { Text("Selecione o tamanho do tabuleiro") },
            trailingIcon = {
                Icon(Icons.Filled.KeyboardArrowDown, "", Modifier.clickable {
                    viewModel.onEvent(FirstScreenEvent.ChangeMenuState(true))
                })
            }
        )
        DropdownMenu(
            expanded = isMenuExpanded,
            onDismissRequest = { viewModel.onEvent(FirstScreenEvent.ChangeMenuState(false)) },
            modifier = Modifier.width(with(LocalDensity.current) {textFiledSize.value.width.toDp()})
        ) {
            listOfOptions.forEach { label ->
                DropdownMenuItem(onClick = {
                    viewModel.onEvent(FirstScreenEvent.ChangeMenuLabel(label))
                    viewModel.onEvent(FirstScreenEvent.ChangeMenuState(false))
                }) {
                    Text(label)
                }
            }
        }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Column {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        if(player1Name != "" && player2Name != "") {
                            viewModel.onEvent(FirstScreenEvent.ErrorSecondTextField(false))
                            viewModel.onEvent(FirstScreenEvent.ErrorFirstTextField(false))
                            navController.navigate("game_screen/$player1Name/$player2Name/$dropDownMenuLabel/$gameType")
                        } else {
                            if(player1Name == "") {
                                if(player2Name != "") viewModel.onEvent(FirstScreenEvent.ErrorSecondTextField(false))
                                viewModel.onEvent(FirstScreenEvent.ErrorFirstTextField(true))
                            }
                            if(player2Name == "") {
                                if(player1Name != "") viewModel.onEvent(FirstScreenEvent.ErrorFirstTextField(false))
                                viewModel.onEvent(FirstScreenEvent.ErrorSecondTextField(true))
                            }
                        }
                    }
                ) {
                    Text("Começar partida")
                }
                Spacer(Modifier.height(smallSpace))
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { navController.navigate(Screen.HistoryScreen.route) }
                ) {
                    Text("Histórico de partidas")
                }
                Spacer(Modifier.height(smallSpace))
            }
        }
    }
}