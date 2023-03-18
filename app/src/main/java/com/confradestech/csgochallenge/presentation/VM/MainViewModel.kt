package com.confradestech.csgochallenge.presentation.VM

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.confradestech.csgochallenge.dataSources.models.dataStates.RunningMatchesListState
import com.confradestech.csgochallenge.dataSources.models.dataStates.UpcomingMatchesListState
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
    var runningMatchesListState by mutableStateOf(RunningMatchesListState())
        private set

    var upcomingMatchesListState by mutableStateOf(UpcomingMatchesListState())
        private set
    //endregion statesForComposeUi

    init {
        fetchMatchesData()
    }

    private fun fetchMatchesData(pageIndex: Int = 1) {
        viewModelScope.launch {
            getRunningMatches(pageIndex)
            getUpcomingMatches(pageIndex)
            delay(3000)
            _isSplashLoading.value = false
        }
    }

    fun getRunningMatches(pageIndex: Int) {
        viewModelScope.launch(Dispatchers.IO) {

            runningMatchesUseCase.get().getRunningMatches(pageIndex).distinctUntilChanged().catch {
                runningMatchesListState = runningMatchesListState.copy(
                    isLoading = false,
                    isError = true,
                    errorMessage = it.localizedMessage,
                )
                it.postException()
            }.collect { runningMatches ->
                runningMatchesListState = runningMatchesListState.copy(
                    isLoading = false,
                    isError = false,
                    errorMessage = null,
                    runningMatches = runningMatches
                )
            }
        }
    }

    fun getUpcomingMatches(pageIndex: Int) {
        viewModelScope.launch(Dispatchers.IO) {

            upcomingMatchesUseCase.get().getUpcomingMatches(pageIndex).distinctUntilChanged().catch {
                upcomingMatchesListState = upcomingMatchesListState.copy(
                    isLoading = false,
                    isError = true,
                    errorMessage = it.localizedMessage,
                )
                it.postException()
            }.collect { upcomingMatches ->
                upcomingMatchesListState = upcomingMatchesListState.copy(
                    isLoading = false,
                    isError = false,
                    errorMessage = null,
                    runningMatches = upcomingMatches
                )
            }
        }
    }
}

