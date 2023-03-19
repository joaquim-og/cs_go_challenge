package com.confradestech.csgochallenge.presentation.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.confradestech.csgochallenge.R
import com.confradestech.csgochallenge.dataSources.models.League
import com.confradestech.csgochallenge.dataSources.models.Opponent
import com.confradestech.csgochallenge.dataSources.models.Serie
import com.confradestech.csgochallenge.presentation.previewAssets.league
import com.confradestech.csgochallenge.presentation.previewAssets.matchStatusNotStarted
import com.confradestech.csgochallenge.presentation.previewAssets.matchStatusStarted
import com.confradestech.csgochallenge.presentation.previewAssets.matchTime
import com.confradestech.csgochallenge.presentation.previewAssets.opponent1
import com.confradestech.csgochallenge.presentation.previewAssets.opponent2
import com.confradestech.csgochallenge.presentation.previewAssets.serie
import com.confradestech.csgochallenge.utilities.ui.theme.CsGoChallengeTheme
import com.confradestech.csgochallenge.utilities.ui.theme.colorText
import com.confradestech.csgochallenge.utilities.ui.theme.grayMatch
import com.confradestech.csgochallenge.utilities.ui.theme.onBackgroundColor
import com.confradestech.csgochallenge.utilities.ui.theme.redMatch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeamsMatchesCard(
    matchTime: String?,
    matchStatus: String?,
    opponent1: Opponent?,
    opponent2: Opponent?,
    league: League?,
    serie: Serie?,
    onclick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(start = 20.dp, end = 20.dp),
        onClick = onclick,
        colors = CardDefaults.cardColors(
            containerColor = onBackgroundColor,
        )
    ) {
        buildHeadContent(matchTime = matchTime, matchStatus = matchStatus)
        buildOpponentsContent(opponent1 = opponent1, opponent2 = opponent2)
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
        )
        buildLeagueContent(league = league, serie = serie)
    }
}

@Composable
private fun buildHeadContent(
    matchTime: String?,
    matchStatus: String?,
) {

    val isMatchStarted = matchStatus.equals(stringResource(id = R.string.match_status_running_flag))

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.End,
    ) {
        Column(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(bottomStart = 20.dp))
                .background(
                    color = if (isMatchStarted) {
                        redMatch
                    } else {
                        grayMatch
                    }
                )
                .padding(top = 5.dp, end = 10.dp, bottom = 5.dp, start = 10.dp)
        ) {
            Text(
                fontSize = 12.sp,
                text = if (isMatchStarted) {
                    stringResource(id = R.string.running_now_match)
                } else {
                    matchTime?.let { it } ?: ""
                },
                color = colorText
            )
        }
    }
}

@Composable
fun buildOpponentsContent(opponent1: Opponent?, opponent2: Opponent?) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 50.dp, end = 50.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            OpponentLogoComponent(opponent1)
            Spacer(Modifier.width(10.dp))
            Text(
                fontSize = 12.sp,
                text = stringResource(id = R.string.match_card_vs_text),
                color = colorText
            )
            Spacer(Modifier.width(10.dp))
            OpponentLogoComponent(opponent2)
        }
    }
}

@Composable
fun buildLeagueContent(league: League?, serie: Serie?) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 5.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center,
    ) {
        LeagueDetailsLogoComponent(league = league, serie = serie)
    }
}

//region previews
@Composable
@Preview(group = "CS GO Challenge", uiMode = Configuration.UI_MODE_NIGHT_NO)
private fun TeamsMatchesCard_Light_Preview() {
    CsGoChallengeTheme {
        TeamsMatchesCard(
            matchStatus = matchStatusNotStarted,
            matchTime = matchTime,
            opponent1 = opponent1,
            opponent2 = opponent2,
            league = league,
            serie = serie,
            onclick = {/*No-op for previews*/ },
        )
    }
}

@Composable
@Preview(group = "CS GO Challenge", uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun TeamsMatchesCard_Dark_Preview() {
    CsGoChallengeTheme {
        TeamsMatchesCard(
            matchStatus = matchStatusNotStarted,
            matchTime = matchTime,
            opponent1 = opponent1,
            opponent2 = opponent2,
            league = league,
            serie = serie,
            onclick = {/*No-op for previews*/ },
        )
    }
}

@Composable
@Preview(group = "CS GO Challenge", uiMode = Configuration.UI_MODE_NIGHT_NO)
private fun TeamsMatchesCard_Now_Match_Light_Preview() {
    CsGoChallengeTheme {
        TeamsMatchesCard(
            matchStatus = matchStatusStarted,
            matchTime = matchTime,
            opponent1 = opponent1,
            opponent2 = opponent2,
            league = league,
            serie = serie,
            onclick = {/*No-op for previews*/ },
        )
    }
}

@Composable
@Preview(group = "CS GO Challenge", uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun TeamsMatchesCard_Now_Match_Dark_Preview() {
    CsGoChallengeTheme {
        TeamsMatchesCard(
            matchStatus = matchStatusStarted,
            matchTime = matchTime,
            opponent1 = opponent1,
            opponent2 = opponent2,
            league = league,
            serie = serie,
            onclick = {/*No-op for previews*/ },
        )
    }
}
//endregion previews