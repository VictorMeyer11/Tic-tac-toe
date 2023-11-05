package com.example.tic_tac_toe.feature_game.presentation.first_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.tic_tac_toe.feature_game.presentation.util.Screen

@Composable
fun FirstScreen(
    navController: NavController,
    viewModel: FirstScreenViewModel = hiltViewModel()
) {
    val listOfOptions = listOf(
        "3x3", "4x4", "5x5", "6x6", "7x7", "8x8", "9x9", "10x10"
    )

    var textFiledSize = remember {
        mutableStateOf(Size.Zero)
    }

    val isMenuExpanded = viewModel.isMenuExpanded.value.isExpanded
    val dropDownMenuLabel = viewModel.menuLabel.value.label

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(Modifier.height(30.dp))
        Text("Tipo de jogo", fontSize = 20.sp)
        Spacer(Modifier.height(15.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black),
                modifier = Modifier.fillMaxWidth(.5f),
                onClick = {}
            ) {
                Text("vs Jogador")
            }
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {}
            ) {
                Text("vs Bot")
            }
        }
        Spacer(Modifier.height(15.dp))
        Text("Nome dos jogadores", fontSize = 20.sp)
        Spacer(Modifier.height(15.dp))
        TextField(
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Jogador 1") },
            value = "",
            onValueChange = {  }
        )
        Spacer(Modifier.height(15.dp))
        TextField(
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Jogador 2") },
            value = "",
            onValueChange = {  },
        )
        Spacer(Modifier.height(15.dp))
        Text("Tamanho do tabuleiro")
        Spacer(Modifier.height(15.dp))
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
                    onClick = { navController.navigate(Screen.GameScreen.route) }
                ) {
                    Text("Começar partida")
                }
                Spacer(Modifier.height(15.dp))
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {}
                ) {
                    Text("Histórico de partidas")
                }
            }
        }
    }
}