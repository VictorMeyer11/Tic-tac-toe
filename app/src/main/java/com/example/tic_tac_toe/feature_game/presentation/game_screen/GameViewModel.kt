package com.example.tic_tac_toe.feature_game.presentation.game_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tic_tac_toe.feature_game.domain.use_case.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.sqrt
import com.example.tic_tac_toe.feature_game.domain.model.MatchResult

@HiltViewModel
class GameViewModel @Inject constructor(
    private val useCases: UseCases
): ViewModel() {
    private val _state = mutableStateOf(GameState())
    val state: State<GameState> = _state

    fun setBoardSize(size: Int) {
        _state.value.buttonValues = Array(size){"-"}
        _state.value.buttonWinners  = Array(size){false}
    }

    fun setButton(id: Int, gameType: String?, player1: String?, player2: String?) {
        if(_state.value.victor == null) {
            if (_state.value.buttonValues[id] == "-") {
                val buttons = _state.value.buttonValues.copyOf()

                when(gameType) {
                    "vsBot" -> {
                        buttons[id] = "X"
                        _state.value = _state.value.copy(buttonValues = buttons)

                        if(!isGameOver(id, player1, player2)) {
                            val botSquareId = botTurn(id, buttons)

                            buttons[botSquareId] = "O"
                            _state.value = _state.value.copy(buttonValues = buttons)
                            isGameOver(botSquareId, player1, player2)
                        }
                    }
                    "vsPlayer" -> {
                        if (_state.value.isPlayer1sTurn) {
                            buttons[id] = "X"
                        } else {
                            buttons[id] = "O"
                        }
                        _state.value = _state.value.copy(
                            buttonValues = buttons,
                            isPlayer1sTurn = !_state.value.isPlayer1sTurn
                        )
                        isGameOver(id, player1, player2)
                    }
                }
            }
        }
    }

    private fun botTurn(id: Int, buttons: Array<String>): Int {
        val lineSize = sqrt(buttons.size.toDouble()).toInt()

        var currentId: Int = if((id % lineSize == 0 || id % lineSize == lineSize - 1) && id + lineSize < buttons.size) {
            id + lineSize
        } else if(id % lineSize == 0 || id % lineSize == lineSize - 1) {
            id - lineSize
        } else if(id + 1 < buttons.size) {
            id + 1
        } else {
            id - 1
        }

        if(buttons[currentId] != "-") {
            for(i in buttons.indices) {
                if(buttons[i] == "-") {
                    currentId = i
                }
            }
        }
        return currentId
    }

    private fun saveMatch(player1: String?, player2: String?) {
        viewModelScope.launch {
            useCases.saveMatch(
                MatchResult(
                    player1 = player1,
                    player2 = player2,
                    winner = if(_state.value.victor == "X") player1!!
                             else player2!!
                )
            )
        }
    }

    private fun isGameOver(id: Int, player1: String?, player2: String?): Boolean {
        if(checkForAWinner(id)) {
            _state.value = _state.value.copy(victor = _state.value.buttonValues[id])
            saveMatch(player1, player2)
            return true
        }
        return false
    }

    private fun checkDiagonally(): Boolean {
        var step: Int = (sqrt(_state.value.buttonValues.size.toDouble()) + 1).toInt()
        var matchFound = true

        for(currentId in (0.._state.value.buttonValues.size-step).step(step)) {
            if(_state.value.buttonValues[currentId] != _state.value.buttonValues[currentId+step] || _state.value.buttonValues[currentId].equals("-")) {
                matchFound = false
                break
            }
        }
        if(matchFound) return true
        step -= 2
        for(currentId in (step.._state.value.buttonValues.size-step-2).step(step)) {
            if(_state.value.buttonValues[currentId] != _state.value.buttonValues[currentId+step]  || _state.value.buttonValues[currentId].equals("-")) {
                return false
            }
        }
        return true
    }

    private fun checkVertically(id: Int): Boolean {
        val step = sqrt(_state.value.buttonValues.size.toDouble()).toInt()

        for(currentId in (id until _state.value.buttonValues.size).step(step)) {
            if(currentId+step > _state.value.buttonValues.size-1) break
            if(_state.value.buttonValues[currentId] != _state.value.buttonValues[currentId+step]  || _state.value.buttonValues[currentId].equals("-")) {
                return false
            }
        }

        for(currentId in (id downTo  0).step(step)) {
            if(currentId-step < 0) break
            if(_state.value.buttonValues[currentId] != _state.value.buttonValues[currentId-step]  || _state.value.buttonValues[currentId].equals("-")) {
                return false
            }
        }
        return true
    }

    private fun getCurrentRow(id: Int): Int {
        var currentId = id
        var currentRow = 1
        val step = sqrt(_state.value.buttonValues.size.toDouble()).toInt()

        while(currentId - step >= 0) {
            currentId -= step
            currentRow++
        }

        return currentRow
    }

    private fun checkHorizontally(id: Int): Boolean {
        val rowSize = sqrt(_state.value.buttonValues.size.toDouble()).toInt()
        val currentRow = getCurrentRow(id)
        val last = rowSize*currentRow - 1
        val first = last - rowSize + 1

        for(currentId in id until last) {
            if(_state.value.buttonValues[currentId] != _state.value.buttonValues[currentId+1]  || _state.value.buttonValues[currentId].equals("-")) {
                return false
            }
        }

        for(currentId in id downTo first+1) {
            if(_state.value.buttonValues[currentId] != _state.value.buttonValues[currentId-1]  || _state.value.buttonValues[currentId].equals("-")) {
                return false
            }
        }
        return true
    }

    private fun checkForAWinner(id: Int): Boolean {
        return checkDiagonally() || checkHorizontally(id) || checkVertically(id)
    }

    fun resetBoard(){
        _state.value = GameState()
    }
}