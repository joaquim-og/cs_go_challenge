package com.confradestech.csgochallenge.domain.remote

import com.confradestech.csgochallenge.BuildConfig.TOKEN
import com.confradestech.csgochallenge.dataSources.response.CsPlayersResponse
import com.confradestech.csgochallenge.dataSources.response.MatchesResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PandaScoreEndpoint {

    @GET("matches/running")
    fun getRunningCsGoMatches(
        @Query("sort") sort: String = "begin_at",
        @Query("page") page: String,
        @Query("per_page") perPage: String = "50",
        @Query("token") token: String = TOKEN,
    ): Flow<Response<MatchesResponse?>>

    @GET("matches/upcoming")
    fun getUpComingCsGoMatches(
        @Query("sort") sort: String = "begin_at",
        @Query("page") page: String,
        @Query("per_page") perPage: String = "50",
        @Query("token") token: String = TOKEN,
    ): Flow<Response<MatchesResponse?>>

    @GET("players")
    fun getPlayers(
        @Query("filterteam_id") teamId: String,
        @Query("page") page: String = "1",
        @Query("per_page") perPage: String = "50",
        @Query("token") token: String = TOKEN,
    ): Flow<Response<CsPlayersResponse?>>

}