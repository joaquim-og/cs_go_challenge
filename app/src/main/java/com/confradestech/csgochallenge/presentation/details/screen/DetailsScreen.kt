package com.confradestech.csgochallenge.presentation.details.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.confradestech.csgochallenge.R
import com.confradestech.csgochallenge.dataSources.models.Game
import com.confradestech.csgochallenge.dataSources.response.MatchesItem
import com.confradestech.csgochallenge.presentation.components.buildOpponentsContent
import com.confradestech.csgochallenge.utilities.extensions.convertTimestampToPrettyDate
import com.confradestech.csgochallenge.utilities.extensions.exceptions.postException
import com.confradestech.csgochallenge.utilities.ui.theme.colorText

@Composable
fun DetailsScreen(
    game: Game?,
    match: MatchesItem?,
    onNavigateBack: () -> Unit,
) {

    AnimatedVisibility(
        visible = true,
        enter = expandVertically() + fadeIn(),
        exit = shrinkVertically() + fadeOut()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .scrollable(rememberScrollState(), Orientation.Vertical),
        ) {

            buildHeaderContent(game = game, match = match, onNavigateBack = onNavigateBack)
            Spacer(modifier = Modifier.height(20.dp))
            buildOpponents(match = match)
            Spacer(modifier = Modifier.height(20.dp))
            buildMatchTime(match = match, game = game)


        }
    }
}

@Composable
private fun buildHeaderContent(game: Game?, match: MatchesItem?, onNavigateBack: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
    ) {

        Column(
            Modifier
                .fillMaxWidth(0.1F)
                .padding(start = 10.dp)
                .clickable { onNavigateBack() },
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                contentDescription = stringResource(id = R.string.generic_back_icon_desc),
                tint = colorText
            )
        }
        val serieName = if (match?.serie?.name.isNullOrEmpty()) {
            ""
        } else {
            match?.serie?.name
        }
        Column(
            Modifier.fillMaxWidth(0.9F),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                fontSize = 18.sp,
                text = "${match?.league?.name} $serieName",
                color = colorText
            )
        }
    }
}

@Composable
private fun buildOpponents(match: MatchesItem?) {
    val opponent1 = try {
        match?.opponents?.get(0)
    } catch (error: Exception) {
        error.postException()
        null
    }

    val opponent2 = try {
        match?.opponents?.get(1)
    } catch (error: Exception) {
        error.postException()
        null
    }
    buildOpponentsContent(opponent1 = opponent1, opponent2 = opponent2)
}

@Composable
private fun buildMatchTime(match: MatchesItem?, game: Game?) {

    val isMatchStarted =
        game?.status.equals(stringResource(id = R.string.match_status_running_flag))

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val matchTime = if (game?.beginAt.isNullOrEmpty()) {
            match?.beginAt
        } else {
            game?.beginAt
        }

        Text(
            fontSize = 18.sp,
            text = if (isMatchStarted) {
                stringResource(id = R.string.running_now_match)
            } else {
                matchTime?.convertTimestampToPrettyDate() ?: ""
            },
            color = colorText
        )
    }
}
