package com.confradestech.csgochallenge.dataSources.domain

import com.google.gson.annotations.SerializedName

data class Streams(
    @SerializedName("embed_url")
    val embedUrl: String,
    val language: String,
    val main: Boolean,
    val official: Boolean,
    @SerializedName("raw_url")
    val rawUrl: String
)