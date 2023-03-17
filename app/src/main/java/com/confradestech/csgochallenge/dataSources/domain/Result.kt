package com.confradestech.csgochallenge.dataSources.domain

import com.google.gson.annotations.SerializedName

data class Result(
    val score: Int,
    @SerializedName("team_id")
    val teamId: Int
)