package com.confradestech.csgochallenge.dataSources.models

import com.google.gson.annotations.SerializedName

data class League(
    val id: Int,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("modified_at")
    val modifiedAt: String,
    val name: String,
    val slug: String,
    val url: String
)