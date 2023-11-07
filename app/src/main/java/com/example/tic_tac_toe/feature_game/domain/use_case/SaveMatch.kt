package com.example.tic_tac_toe.feature_game.domain.use_case

import com.example.tic_tac_toe.feature_game.domain.repository.AppRepository
import com.example.tic_tac_toe.feature_game.domain.model.MatchResult

class SaveMatch(
    private val repository: AppRepository,
) {
    suspend operator fun invoke(matchResult: MatchResult) {
        repository.saveMatch(matchResult)
    }
}