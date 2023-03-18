package com.confradestech.csgochallenge.domain.repository.mappers

import com.confradestech.csgochallenge.dataSources.response.MatchesItem
import com.confradestech.csgochallenge.dataSources.response.MatchesResponse
import javax.inject.Inject

class MatchesItemMapper @Inject constructor() {

    fun mapMatchesList(responseList: MatchesResponse): List<MatchesItem> {
        val updatedList = mutableListOf<MatchesItem>()

        responseList.forEach { responseObject ->
            updatedList.add(responseObject)

        }
        return updatedList
    }

}