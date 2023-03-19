package com.confradestech.csgochallenge.presentation.components

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.confradestech.csgochallenge.R
import com.confradestech.csgochallenge.dataSources.response.CsPlayersItem
import com.confradestech.csgochallenge.presentation.previewAssets.player
import com.confradestech.csgochallenge.utilities.ui.theme.CsGoChallengeTheme
import com.confradestech.csgochallenge.utilities.ui.theme.colorText
import com.confradestech.csgochallenge.utilities.ui.theme.onBackgroundColor

@Composable
fun PlayersListStartComponent(opponent: CsPlayersItem?) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(top = 10.dp)
            .clip(shape = RoundedCornerShape(bottomEnd = 20.dp, topEnd = 20.dp))
            .background(onBackgroundColor),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start,
    ) {
        buildPlayerStartCard(opponent)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun buildPlayerStartCard(player: CsPlayersItem?) {
    Card(
        modifier = Modifier
            .fillMaxSize(),
        onClick = { /*NO-OP*/ },
        colors = CardDefaults.cardColors(
            containerColor = onBackgroundColor,
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(top = 10.dp),
                horizontalAlignment = Alignment.End,
            ) {
                Text(
                    fontWeight = FontWeight.Bold,
                    fontSize = 10.sp,
                    text = player?.slug
                        ?: stringResource(id = R.string.generic_team_name_desc),
                    color = colorText
                )
                Text(
                    fontSize = 8.sp,
                    text = player?.name
                        ?: stringResource(id = R.string.generic_team_name_desc),
                    color = colorText
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(start = 10.dp)
            ) {

                val isImageUrlMissing = player?.imageUrl.isNullOrEmpty()

                if (isImageUrlMissing) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_broken_image_24),
                        contentDescription = stringResource(id = R.string.generic_logo_player_desc),
                        tint = colorText
                    )
                } else {
                    Image(
                        painter = rememberImagePainter(player?.imageUrl),
                        contentDescription = player?.slug,
                        contentScale = ContentScale.Inside
                    )
                }

            }
        }
    }
}

//region previews
@Composable
@Preview(group = "CS GO Challenge", uiMode = Configuration.UI_MODE_NIGHT_NO)
private fun PlayersListComponent_Light_Preview() {
    CsGoChallengeTheme {
        PlayersListStartComponent(opponent = player)
    }
}

@Composable
@Preview(group = "CS GO Challenge", uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun PlayersListComponent_Dark_Preview() {
    CsGoChallengeTheme {
        PlayersListStartComponent(opponent = player)
    }
}
//endregion previews