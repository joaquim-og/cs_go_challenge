package com.confradestech.csgochallenge.dataSources.domain

import com.google.gson.annotations.SerializedName

data class Serie(
    @SerializedName("begin_at")
    val beginAt: String,
    @SerializedName("end_at")
    val endAt: String,
    @SerializedName("full_name")
    val fullName: String,
    val id: Int,
    @SerializedName("league_id")
    val leagueId: Int,
    @SerializedName("modified_at")
    val modifiedAt: String,
    val name: String,
    val season: String,
    val slug: String,
    @SerializedName("winner_id")
    val winnerId: Int,
    @SerializedName("winner_type")
    val winnerType: String,
    val year: Int
)