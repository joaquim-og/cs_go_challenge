package com.confradestech.csgochallenge.dataSources.domain

import com.google.gson.annotations.SerializedName

data class Game(
    @SerializedName("begin_at")
    val beginAt: String,
    val complete: Boolean,
    @SerializedName("detailed_stats")
    val detailedStats: Boolean,
    @SerializedName("endAt")
    val endAt: String,
    val finished: Boolean,
    val forfeit: Boolean,
    val id: Int,
    val length: Int,
    @SerializedName("match_id")
    val matchId: Int,
    val position: Int,
    val status: String,
    val winner: Winner,
    @SerializedName("winner_type")
    val winnerType: String
)