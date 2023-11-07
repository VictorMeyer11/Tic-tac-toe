package com.example.tic_tac_toe.feature_game.domain.repository
import com.example.tic_tac_toe.feature_game.domain.model.MatchResult
import kotlinx.coroutines.flow.Flow

interface AppRepository {
    fun getMatchHistory(): Flow<List<MatchResult>>

    suspend fun saveMatch(matchResult: MatchResult)
}