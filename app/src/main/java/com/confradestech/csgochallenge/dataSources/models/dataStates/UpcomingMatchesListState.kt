package com.confradestech.csgochallenge.dataSources.models.dataStates

import com.confradestech.csgochallenge.dataSources.response.MatchesItem


data class UpcomingMatchesListState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isError: Boolean = false,
    val runningMatches:  List<MatchesItem?>? = emptyList(),
)
