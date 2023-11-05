package com.example.tic_tac_toe.feature_game.data.repository

import com.example.tic_tac_toe.feature_game.data.data_source.AppDao
import com.example.tic_tac_toe.feature_game.domain.repository.AppRepository

class AppRepositoryImplementation(
    private val dao: AppDao
): AppRepository {

}