package com.confradestech.csgochallenge.presentation.components

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.confradestech.csgochallenge.R
import com.confradestech.csgochallenge.dataSources.models.League
import com.confradestech.csgochallenge.dataSources.models.Serie
import com.confradestech.csgochallenge.presentation.previewAssets.league
import com.confradestech.csgochallenge.presentation.previewAssets.serie
import com.confradestech.csgochallenge.utilities.ui.theme.CsGoChallengeTheme
import com.confradestech.csgochallenge.utilities.ui.theme.colorText

@Composable
fun LeagueDetailsLogoComponent(league: League?, serie: Serie?) {
    Row(
        Modifier
            .height(20.dp)
            .fillMaxWidth()
            .padding(start = 20.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
    ) {

        val isImageUrlMissing = league?.imageUrl?.isNullOrEmpty()

        Column(
            Modifier.width(20.dp),
        ) {
            if (isImageUrlMissing == true) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_broken_image_24),
                    contentDescription = stringResource(id = R.string.generic_logo_league_desc),
                    tint = colorText
                )
            } else {
            Image(
                painter = rememberImagePainter(league?.imageUrl),
                contentDescription = league?.slug,
                contentScale = ContentScale.Inside
            )
            }
        }
        Spacer(modifier = Modifier.width(10.dp))
        val serieName = if (serie?.name.isNullOrEmpty()) {
            ""
        } else {
            serie?.name
        }
        Text(
            fontSize = 12.sp,
            text = "${league?.name} $serieName",
            color = colorText
        )
    }
}

//region previews
@Composable
@Preview(group = "CS GO Challenge", uiMode = Configuration.UI_MODE_NIGHT_NO)
private fun LeagueDetailsLogoComponent_Light_Preview() {
    CsGoChallengeTheme {
        LeagueDetailsLogoComponent(league = league, serie = serie)
    }
}

@Composable
@Preview(group = "CS GO Challenge", uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun LeagueDetailsLogoComponent_Dark_Preview() {
    CsGoChallengeTheme {
        LeagueDetailsLogoComponent(league = league, serie = serie)
    }
}
//endregion previews