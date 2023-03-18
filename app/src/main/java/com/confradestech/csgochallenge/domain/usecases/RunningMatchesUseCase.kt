package com.confradestech.csgochallenge.domain.usecases

import com.confradestech.csgochallenge.dataSources.response.MatchesItem
import com.confradestech.csgochallenge.domain.repository.PandaScorePointRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow

class RunningMatchesUseCase(private val repository: PandaScorePointRepository) {

    fun getRunningMatches(pageIndex: Int): Flow<List<MatchesItem?>?> =
        channelFlow {
            repository.getRunningCsGoMatches(pageIndex.toString()).collect {
                send(it)
            }
        }
}