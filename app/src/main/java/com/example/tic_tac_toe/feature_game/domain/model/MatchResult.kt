package com.example.tic_tac_toe.feature_game.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MatchResult(
    var sum: String,
    @PrimaryKey var result: String
) {

}