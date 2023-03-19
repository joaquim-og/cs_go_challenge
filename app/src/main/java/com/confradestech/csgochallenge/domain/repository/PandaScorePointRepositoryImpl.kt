package com.confradestech.csgochallenge.domain.repository

import com.confradestech.csgochallenge.dataSources.response.CsPlayersItem
import com.confradestech.csgochallenge.dataSources.response.MatchesItem
import com.confradestech.csgochallenge.domain.remote.PandaScoreEndpoint
import com.confradestech.csgochallenge.domain.repository.mappers.MatchesItemMapper
import com.confradestech.csgochallenge.domain.repository.mappers.PlayersItemMapper
import com.confradestech.csgochallenge.utilities.extensions.exceptions.postException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PandaScorePointRepositoryImpl @Inject constructor(
    private val pandaScoreEndpoint: PandaScoreEndpoint,
    private val matchesItemMapper: MatchesItemMapper,
    private val playersItemMapper: PlayersItemMapper
) : PandaScorePointRepository {

    override fun getRunningCsGoMatches(
        page: String,
    ): Flow<List<MatchesItem?>?> = flow {
        runCatching {
            pandaScoreEndpoint.getRunningCsGoMatches(
                page = page
            )
        }.onSuccess { responseFlow ->
            responseFlow.collect {
                val body = it.body()
                body?.let {
                    emit(matchesItemMapper.mapMatchesList(body))
                } ?: emit(null)
            }

        }.onFailure {
            it.postException()
            emit(null)
            throw it
        }
    }.flowOn(Dispatchers.IO)


    override fun getUpComingCsGoMatches(
        page: String,
    ): Flow<List<MatchesItem?>?> = flow {
        runCatching {
            pandaScoreEndpoint.getUpComingCsGoMatches(
                page = page
            )
        }.onSuccess { responseFlow ->
            responseFlow.collect {
                val body = it.body()
                body?.let {
                    emit(matchesItemMapper.mapMatchesList(body))
                } ?: emit(null)
            }

        }.onFailure {
            it.postException()
            emit(null)
            throw it
        }
    }.flowOn(Dispatchers.IO)

    override fun getPlayers(
        teamId: String,
    ): Flow<List<CsPlayersItem?>?> = flow {
        runCatching {
            pandaScoreEndpoint.getPlayers(
                teamId = teamId
            )
        }.onSuccess { responseFlow ->
            responseFlow.collect {
                val body = it.body()
                body?.let {
                    emit(playersItemMapper.mapMatchesList(body))
                } ?: emit(null)
            }

        }.onFailure {
            it.postException()
            emit(null)
            throw it
        }
    }.flowOn(Dispatchers.IO)

}
