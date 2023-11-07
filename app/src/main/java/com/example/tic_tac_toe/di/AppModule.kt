package com.example.tic_tac_toe.di

import android.app.Application
import androidx.room.Room
import com.example.tic_tac_toe.feature_game.data.data_source.AppDataBase
import com.example.tic_tac_toe.feature_game.data.repository.AppRepositoryImplementation
import com.example.tic_tac_toe.feature_game.domain.repository.AppRepository
import com.example.tic_tac_toe.feature_game.domain.use_case.GetMatchHistory
import com.example.tic_tac_toe.feature_game.domain.use_case.SaveMatch
import com.example.tic_tac_toe.feature_game.domain.use_case.UseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideDatabase(app: Application): AppDataBase {
        return Room.databaseBuilder(
            app,
            AppDataBase::class.java,
            AppDataBase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideAppRepository(db: AppDataBase): AppRepository {
        return AppRepositoryImplementation(db.appDao)
    }

    @Provides
    @Singleton
    fun provideUseCases(repository: AppRepository): UseCases {
        return UseCases(
            getMatchHistory = GetMatchHistory(repository),
            saveMatch = SaveMatch(repository)
        )
    }
}