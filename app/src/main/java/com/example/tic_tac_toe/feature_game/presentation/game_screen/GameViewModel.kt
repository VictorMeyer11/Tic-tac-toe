package com.example.tic_tac_toe.feature_game.presentation.game_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlin.math.sqrt

class GameViewModel: ViewModel() {
    private val _state = mutableStateOf(GameState())
    val state: State<GameState> = _state

    fun setBoardSize(size: Int) {
        _state.value.buttonValues = Array(size){"-"}
        _state.value.buttonWinners  = Array(size){false}
    }

    fun setButton(id: Int) {
        if(_state.value.victor == null) {
            if (_state.value.buttonValues[id] == "-") {
                val buttons = _state.value.buttonValues.copyOf()

                if (_state.value.isPlayer1sTurn) {
                    buttons[id] = "X"
                } else {
                    buttons[id] = "O"
                }
                _state.value = _state.value.copy(
                    buttonValues = buttons,
                    isPlayer1sTurn = !_state.value.isPlayer1sTurn
                )
            }
        }
        isGameOver(id)
    }

    fun botTurn(id: Int) {
        if(_state.value.victor == null) {
            if (_state.value.buttonValues[id] == "-") {
                val buttons = _state.value.buttonValues.copyOf()

                if (_state.value.isPlayer1sTurn) {
                    buttons[id] = "X"
                } else {

                }
                _state.value = _state.value.copy(
                    buttonValues = buttons,
                    isPlayer1sTurn = !_state.value.isPlayer1sTurn
                )
            }
        }
        isGameOver(id)
    }

    private fun isGameOver(id: Int): Boolean {
        if(checkForAWinner(id)) {
            _state.value = _state.value.copy(victor = _state.value.buttonValues[id])
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
        val last = (rowSize-1)*currentRow
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
//        val firstTwoMatch = _state.value.buttonValues[first] == _state.value.buttonValues[second]
//        val secondTwoMatch = _state.value.buttonValues[second] == _state.value.buttonValues[third]
//        val allThreeMatch = firstTwoMatch && secondTwoMatch
//        return if(_state.value.buttonValues[first] == "-") {
//            false
//        } else if(allThreeMatch){
//            _state.value = _state.value.copy(victor = _state.value.buttonValues[first])
//            val buttonWinners = _state.value.buttonWinners.copyOf()
//            buttonWinners[first] = true
//            buttonWinners[second] = true
//            buttonWinners[third] = true
//            _state.value = _state.value.copy(buttonWinners = buttonWinners)
//            true
//        } else {
//            false
//        }
    }

    fun resetBoard(){
        _state.value = GameState()
    }
}