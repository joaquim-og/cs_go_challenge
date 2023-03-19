package com.confradestech.csgochallenge.domain.usecases

import com.confradestech.csgochallenge.dataSources.response.CsPlayersItem
import com.confradestech.csgochallenge.dataSources.response.MatchesItem
import com.confradestech.csgochallenge.domain.repository.PandaScorePointRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow

class GetPlayerListUseCase(private val repository: PandaScorePointRepository) {

    fun getPlayerList(teamId: Int): Flow<List<CsPlayersItem?>?> =
        channelFlow {
            repository.getPlayers(teamId.toString()).collect {
                send(it)
            }
        }
}