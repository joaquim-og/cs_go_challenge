package com.confradestech.csgochallenge.dataSources.models

import com.google.gson.annotations.SerializedName

data class Opponent(
    @SerializedName("opponent")
    val opponentDetails: OpponentDetails,
    val type: String
)