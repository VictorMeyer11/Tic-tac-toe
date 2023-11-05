package com.example.tic_tac_toe.feature_game.presentation.first_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.tic_tac_toe.feature_game.domain.use_case.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FirstScreenViewModel @Inject constructor(
    private val useCases: UseCases
): ViewModel() {
    private val _isMenuExpanded = mutableStateOf(DropDownMenuState(isExpanded = false))
    val isMenuExpanded: State<DropDownMenuState> = _isMenuExpanded

    private val _menuLabel = mutableStateOf(DropDownMenuState(label = "3x3"))
    val menuLabel: State<DropDownMenuState> = _menuLabel

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
        }
    }
}