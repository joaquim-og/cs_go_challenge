package com.confradestech.csgochallenge.presentation.VM

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.confradestech.csgochallenge.dataSources.models.Game
import com.confradestech.csgochallenge.dataSources.models.Opponent
import com.confradestech.csgochallenge.dataSources.models.dataStates.MatchesListState
import com.confradestech.csgochallenge.dataSources.models.dataStates.SelectedGameState
import com.confradestech.csgochallenge.dataSources.response.MatchesItem
import com.confradestech.csgochallenge.domain.usecases.GetPlayerListUseCase
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
    private val upcomingMatchesUseCase: Lazy<UpcomingMatchesUseCase>,
    private val getPlayerListUseCase: Lazy<GetPlayerListUseCase>
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

    private fun getRemoteOpponent1CsPlayers(
        teamId: Int?,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            setSelectedGameLoadingState()
            getPlayerListUseCase.get().getPlayerList().distinctUntilChanged()
                .catch {
                    setSelectedGameErrorState(it)
                    it.postException()
                }.collect { playersList ->

                    val teamPlayersList = playersList?.filter { it?.currentTeam?.id == teamId }

                    println("Xablau aqui o teamPlayer List 1 -> $teamPlayersList")
                    println("Xablau aqui o teamPlayer List 1 size -> ${teamPlayersList?.size}")
                    selectedGameState = selectedGameState.copy(
                        playerListOpponent1 = teamPlayersList,
                    )
                }
        }
    }

    private fun getRemoteOpponent2CsPlayers(
        teamId: Int?,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            setSelectedGameLoadingState()
            getPlayerListUseCase.get().getPlayerList().distinctUntilChanged()
                .catch {
                    setSelectedGameErrorState(it)
                    it.postException()
                }.collect { playersList ->

                    val teamPlayersList = playersList?.filter { it?.currentTeam?.id == teamId }

                    selectedGameState = selectedGameState.copy(
                        isLoading = false,
                        playerListOpponent2 = teamPlayersList,
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

    private fun setSelectedGameErrorState(error: Throwable) {
        selectedGameState = selectedGameState.copy(
            isLoading = false,
            isError = true,
            errorMessage = error.localizedMessage,
            match = null,
            game = null,
            playerListOpponent1 = null,
            playerListOpponent2 = null,
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
        val opponent1 = try {
            match?.opponents?.get(0)
        } catch (error: Exception) {
            error.postException()
            null
        }
        getRemoteOpponent1CsPlayers(
            teamId = opponent1?.opponentDetails?.id,
        )

        val opponent2 = try {
            match?.opponents?.get(1)
        } catch (error: Exception) {
            error.postException()
            null
        }
        getRemoteOpponent2CsPlayers(
            teamId = opponent2?.opponentDetails?.id,
        )
    }

    private fun setSelectedGameLoadingState() {
        selectedGameState = selectedGameState.copy(
            isLoading = true,
        )
    }
}

