package com.confradestech.csgochallenge.domain.repository.mappers

import com.confradestech.csgochallenge.dataSources.response.CsPlayersItem
import com.confradestech.csgochallenge.dataSources.response.CsPlayersResponse
import javax.inject.Inject

class PlayersItemMapper @Inject constructor() {

    fun mapMatchesList(responseList: CsPlayersResponse): List<CsPlayersItem> {
        val updatedList = mutableListOf<CsPlayersItem>()

        responseList.forEach { responseObject ->
            updatedList.add(responseObject)

        }
        return updatedList
    }

}