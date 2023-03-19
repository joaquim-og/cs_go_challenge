package com.confradestech.csgochallenge.dataSources.models.dataStates

import com.confradestech.csgochallenge.dataSources.models.Game
import com.confradestech.csgochallenge.dataSources.response.MatchesItem


data class SelectedGameState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isError: Boolean = false,
    val match:  MatchesItem? = null,
    val game:  Game? = null,
)
