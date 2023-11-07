package com.example.tic_tac_toe.feature_game.data.data_source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import com.example.tic_tac_toe.feature_game.domain.model.MatchResult

@Dao
interface AppDao {
    @Query("SELECT * FROM matchresult")
    fun getMatchHistory(): Flow<List<MatchResult>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMatch(matchResult: MatchResult)
}