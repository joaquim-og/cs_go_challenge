package com.confradestech.csgochallenge.dataSources.response

import com.confradestech.csgochallenge.dataSources.models.CurrentTeam
import com.confradestech.csgochallenge.dataSources.models.CurrentVideogame
import com.google.gson.annotations.SerializedName

data class CsPlayersItem(
    val age: String?,
    val birthday: String?,
    @SerializedName("current_team")
    val currentTeam: CurrentTeam?,
    @SerializedName("current_videogame")
    val currentVideogame: CurrentVideogame?,
    @SerializedName("first_name")
    val firstName: String?,
    val id: Int?,
    @SerializedName("image_url")
    val imageUrl: String?,
    @SerializedName("last_name")
    val lastName: String?,
    @SerializedName("modified_at")
    val modifiedAt: String?,
    val name: String?,
    val nationality: String?,
    val role: String?,
    val slug: String?
)