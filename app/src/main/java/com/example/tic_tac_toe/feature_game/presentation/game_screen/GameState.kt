package com.example.tic_tac_toe.feature_game.presentation.game_screen

data class GameState(
    var buttonValues: Array<String> = emptyArray(),
    var buttonWinners: Array<Boolean> = emptyArray(),
    val isPlayer1sTurn : Boolean = true,
    val victor : String? = null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as GameState

        if (!buttonValues.contentEquals(other.buttonValues)) return false
        if (!buttonWinners.contentEquals(other.buttonWinners)) return false
        if (isPlayer1sTurn != other.isPlayer1sTurn) return false
        if (victor != other.victor) return false

        return true
    }

    override fun hashCode(): Int {
        var result = buttonValues.contentHashCode()
        result = 31 * result + buttonWinners.contentHashCode()
        result = 31 * result + isPlayer1sTurn.hashCode()
        result = 31 * result + (victor?.hashCode() ?: 0)
        return result
    }
}