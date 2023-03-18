package com.confradestech.csgochallenge.domain.usecases

import com.confradestech.csgochallenge.dataSources.response.MatchesItem
import com.confradestech.csgochallenge.domain.repository.PandaScorePointRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow

class UpcomingMatchesUseCase(private val repository: PandaScorePointRepository) {

    fun getUpcomingMatches(pageIndex: Int): Flow<List<MatchesItem?>?> =
        channelFlow {
            repository.getUpComingCsGoMatches(pageIndex.toString()).collect {
                send(it)
            }
        }
}