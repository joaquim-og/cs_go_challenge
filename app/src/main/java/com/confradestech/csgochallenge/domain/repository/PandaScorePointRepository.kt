package com.confradestech.csgochallenge.domain.repository

import com.confradestech.csgochallenge.dataSources.response.MatchesItem
import kotlinx.coroutines.flow.Flow

interface PandaScorePointRepository {

    fun getRunningCsGoMatches(
        page: String,
    ): Flow<List<MatchesItem?>?>

    fun getUpComingCsGoMatches(
        page: String,
    ): Flow<List<MatchesItem?>?>

}