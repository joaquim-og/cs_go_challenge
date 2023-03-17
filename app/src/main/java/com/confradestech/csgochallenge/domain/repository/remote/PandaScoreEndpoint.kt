package com.confradestech.csgochallenge.domain.repository.remote

import com.confradestech.csgochallenge.dataSources.response.MatchesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PandaScoreEndpoint {

    @GET("/running")
    fun getRunningCsGoMatches(
        @Query("sort") sort: String = "begin_at",
        @Query("page") page: String,
        @Query("per_page") perPage: String = "50",
    ): Call<MatchesResponse>

    @GET("/upcoming")
    fun getUpComingCsGoMatches(
        @Query("sort") sort: String = "begin_at",
        @Query("page") page: String,
        @Query("per_page") perPage: String = "50",
    ): Call<MatchesResponse>

}