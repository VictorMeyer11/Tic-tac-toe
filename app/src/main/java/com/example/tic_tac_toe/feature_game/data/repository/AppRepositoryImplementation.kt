package com.example.tic_tac_toe.feature_game.data.repository

import com.example.tic_tac_toe.feature_game.data.data_source.AppDao
import com.example.tic_tac_toe.feature_game.domain.repository.AppRepository
import kotlinx.coroutines.flow.Flow
import com.example.tic_tac_toe.feature_game.domain.model.MatchResult

class AppRepositoryImplementation(
    private val dao: AppDao
): AppRepository {
    override fun getMatchHistory(): Flow<List<MatchResult>> {
        return dao.getMatchHistory()
    }

    override suspend fun saveMatch(matchResult: MatchResult) {
       dao.saveMatch(matchResult)
    }
}