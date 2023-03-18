package com.confradestech.csgochallenge.dataSources.response

import com.confradestech.csgochallenge.dataSources.models.Game
import com.confradestech.csgochallenge.dataSources.models.League
import com.confradestech.csgochallenge.dataSources.models.Live
import com.confradestech.csgochallenge.dataSources.models.Opponent
import com.confradestech.csgochallenge.dataSources.models.Result
import com.confradestech.csgochallenge.dataSources.models.Serie
import com.confradestech.csgochallenge.dataSources.models.Streams
import com.confradestech.csgochallenge.dataSources.models.Tournament
import com.confradestech.csgochallenge.dataSources.models.Videogame
import com.confradestech.csgochallenge.dataSources.models.VideogameVersion
import com.confradestech.csgochallenge.dataSources.models.Winner
import com.google.gson.annotations.SerializedName

data class MatchesItem(
    @SerializedName("begin_at")
    val beginAt: String?,
    @SerializedName("detailed_stats")
    val detailedStats: Boolean?,
    val draw: Boolean?,
    @SerializedName("end_at")
    val endAt: String?,
    val forfeit: Boolean?,
    @SerializedName("game_advantage")
    val gameAdvantage: Int?,
    val games: List<Game>?,
    val id: Int?,
    val league: League?,
    @SerializedName("league_id")
    val leagueId: Int?,
    val live: Live?,
    @SerializedName("match_type")
    val matchType: String?,
    @SerializedName("modified_at")
    val modifiedAt: String?,
    val name: String?,
    @SerializedName("number_of_games")
    val numberOfGames: Int?,
    val opponents: List<Opponent>?,
    @SerializedName("original_scheduled_at")
    val originalScheduledAt: String?,
    val rescheduled: Boolean?,
    val results: List<Result>?,
    @SerializedName("scheduled_at")
    val scheduledAt: String?,
    val serie: Serie?,
    @SerializedName("serie_id")
    val serieId: Int?,
    val slug: String?,
    val status: String?,
    @SerializedName("streams_list")
    val streamsList: List<Streams>?,
    val tournament: Tournament?,
    @SerializedName("tournament_id")
    val tournamentId: Int?,
    val videogame: Videogame?,
    @SerializedName("videogame_version")
    val videogameVersion: VideogameVersion?,
    val winner: Winner?,
    @SerializedName("winner_id")
    val winnerId: Int?,
    @SerializedName("winner_type")
    val winnerType: String?
)