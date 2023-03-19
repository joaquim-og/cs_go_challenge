package com.confradestech.csgochallenge.presentation.previewAssets

import com.confradestech.csgochallenge.dataSources.models.League
import com.confradestech.csgochallenge.dataSources.models.Opponent
import com.confradestech.csgochallenge.dataSources.models.OpponentDetails
import com.confradestech.csgochallenge.dataSources.models.Serie
import com.confradestech.csgochallenge.utilities.extensions.convertTimestampToPrettyDate

val matchTime = "2023-03-21T13:00:00Z".convertTimestampToPrettyDate()

val matchStatusNotStarted = "not_started"

val matchStatusStarted = "running"

val opponentDetails = OpponentDetails(
    acronym = "",
    id = 126408,
    imageUrl = "https://cdn.pandascore.co/images/team/image/126408/d8_wxn_rx.png",
    location = "SE",
    modifiedAt = "2023-02-22T02:31:59Z",
    name = "9INE",
    slug = "9INE",
)

val opponent1 = Opponent(
    opponentDetails = opponentDetails,
    type = "Team",
)

val opponent2 = Opponent(
    opponentDetails = opponentDetails.copy(
        imageUrl = "https://cdn.pandascore.co/images/team/image/126208/illuminar.png",
        name = "Illuminar",
        slug = "Illuminar",
    ),
    type = "Team",
)

val league = League(
    id = 4372,
    imageUrl = "https://cdn.pandascore.co/images/league/image/4372/600px-Elisa_Invitational.png",
    modifiedAt = "2021-05-01T19:14:42Z",
    name = "Elisa Invitational",
    slug = "cs-go-elisa-invitational",
    url = "https://elisaesports.fi",
)

val serie = Serie(
    beginAt = "2023-01-16T10:00:00Z",
    endAt = "",
    fullName = "Winter 2023",
    id = 5521,
    leagueId = 4372,
    modifiedAt = "2023-01-14T08:38:25Z",
    name = "Serie Name",
    season = "Winter",
    slug = "cs-go-elisa-invitational-winter-2023",
    winnerId = 0,
    winnerType = "",
    year = 2023
)