package com.example.tic_tac_toe.feature_game.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tic_tac_toe.feature_game.domain.model.MatchResult

@Database(
    entities = [MatchResult::class],
    version = 1
)

abstract class AppDataBase: RoomDatabase() {
    abstract val appDao: AppDao

    companion object {
        const val DATABASE_NAME = "app_db"
    }
}