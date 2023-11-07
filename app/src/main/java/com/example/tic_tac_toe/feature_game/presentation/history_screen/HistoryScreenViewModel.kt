package com.example.tic_tac_toe.feature_game.presentation.history_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tic_tac_toe.feature_game.domain.use_case.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.example.tic_tac_toe.feature_game.domain.model.MatchResult
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@HiltViewModel
class HistoryScreenViewModel @Inject constructor(
    private val useCases: UseCases
): ViewModel() {
    private var _matchHistory = mutableStateOf<List<MatchResult>>(listOf())
    var matchHistory: State<List<MatchResult>> = _matchHistory

    init {
        useCases.getMatchHistory().onEach { matches ->
            _matchHistory.value = matches
        }.launchIn(viewModelScope)
    }
}