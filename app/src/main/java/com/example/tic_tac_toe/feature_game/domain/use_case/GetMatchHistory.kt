package com.example.tic_tac_toe.feature_game.domain.use_case

import com.example.tic_tac_toe.feature_game.domain.repository.AppRepository
import kotlinx.coroutines.flow.Flow
import com.example.tic_tac_toe.feature_game.domain.model.MatchResult

class GetMatchHistory(private val repository: AppRepository) {
    operator fun invoke(): Flow<List<MatchResult>> {
        return repository.getMatchHistory()
    }
}