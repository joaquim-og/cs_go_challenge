package com.confradestech.csgochallenge.dataSources.domain

import com.google.gson.annotations.SerializedName

data class Live(
    @SerializedName("opens_at")
    val opensAt: String,
    val supported: Boolean,
    val url: String
)