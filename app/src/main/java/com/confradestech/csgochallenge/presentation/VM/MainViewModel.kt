package com.confradestech.csgochallenge.presentation.VM

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.confradestech.csgochallenge.dataSources.models.Game
import com.confradestech.csgochallenge.dataSources.models.dataStates.MatchesListState
import com.confradestech.csgochallenge.dataSources.models.dataStates.SelectedGameState
import com.confradestech.csgochallenge.dataSources.response.MatchesItem
import com.confradestech.csgochallenge.domain.usecases.RunningMatchesUseCase
import com.confradestech.csgochallenge.domain.usecases.UpcomingMatchesUseCase
import com.confradestech.csgochallenge.utilities.extensions.exceptions.postException
import dagger.Lazy
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val runningMatchesUseCase: Lazy<RunningMatchesUseCase>,
    private val upcomingMatchesUseCase: Lazy<UpcomingMatchesUseCase>
) : ViewModel() {

    private val _isSplashLoading = MutableStateFlow(true)
    val isSplashLoading = _isSplashLoading.asStateFlow()

    //region statesForComposeUi
    var matchesListState by mutableStateOf(MatchesListState())
        private set
    var selectedGameState by mutableStateOf(SelectedGameState())
        private set
    //endregion statesForComposeUi

    init {
        fetchMatchesData()
        updateSplashScreenCounter()
    }

    private fun updateSplashScreenCounter() {
        viewModelScope.launch {
            delay(3000)
            _isSplashLoading.value = false
        }
    }

    fun fetchMatchesData(pageIndex: Int = 1) {
        viewModelScope.launch {
            getRunningMatches(pageIndex)
        }
    }

    private fun getRunningMatches(pageIndex: Int) {
        setMatchesLoadingState()
        viewModelScope.launch(Dispatchers.IO) {
            runningMatchesUseCase.get().getRunningMatches(pageIndex).distinctUntilChanged().catch {
                setMatchesErrorState(it)
                it.postException()
            }.collect { runningMatches ->
                matchesListState = matchesListState.copy(
                    isError = false,
                    errorMessage = null,
                    matches = runningMatches
                )
                getUpcomingMatches(pageIndex)
            }
        }
    }

    private fun getUpcomingMatches(pageIndex: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val actualMatchesList = mutableListOf<MatchesItem>()

            matchesListState.matches?.forEach { matchItem ->
                matchItem?.let {
                    actualMatchesList.add(it)
                }
            }

            upcomingMatchesUseCase.get().getUpcomingMatches(pageIndex).distinctUntilChanged()
                .catch {
                    setMatchesErrorState(it)
                    it.postException()
                }.collect { upcomingMatches ->

                    upcomingMatches?.forEach { upcomingMatch ->
                        upcomingMatch?.let {
                            actualMatchesList.add(it)
                        }
                    }

                    matchesListState = matchesListState.copy(
                        isLoading = false,
                        isError = false,
                        errorMessage = null,
                        matches = actualMatchesList.toList()
                    )
                }
        }
    }

    private fun setMatchesLoadingState() {
        matchesListState = matchesListState.copy(
            isLoading = true,
            isError = false,
            errorMessage = null,
            matches = null,
        )
    }

    private fun setMatchesErrorState(error: Throwable) {
        matchesListState = matchesListState.copy(
            isLoading = false,
            isError = true,
            errorMessage = error.localizedMessage,
        )
    }

    fun setSelectedGameValues(match: MatchesItem?, game: Game?) {
        selectedGameState = selectedGameState.copy(
            isLoading = false,
            isError = false,
            errorMessage = null,
            match = match,
            game = game,
        )
    }
}

