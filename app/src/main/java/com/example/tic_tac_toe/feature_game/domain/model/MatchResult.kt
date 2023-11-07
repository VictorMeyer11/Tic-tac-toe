package com.example.tic_tac_toe.feature_game.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.annotation.Nonnull

@Entity
data class MatchResult(
    var player1: String?,
    var player2: String?,
    @PrimaryKey var winner: String
)