package com.confradestech.csgochallenge.dataSources.models

import com.google.gson.annotations.SerializedName

data class Tournament(
    @SerializedName("begin_at")
    val beginAt: String,
    @SerializedName("end_at")
    val endAt: String,
    @SerializedName("has_bracket")
    val hasBracket: Boolean,
    val id: Int,
    @SerializedName("league_id")
    val leagueId: Int,
    @SerializedName("live_supported")
    val liveSupported: Boolean,
    @SerializedName("modified_at")
    val modifiedAt: String,
    val name: String,
    val prizepool: String,
    @SerializedName("serie_id")
    val serieId: Int,
    val slug: String,
    val tier: String,
    @SerializedName("winner_id")
    val winnerId: Int,
    @SerializedName("winner_type")
    val winnerType: String
)