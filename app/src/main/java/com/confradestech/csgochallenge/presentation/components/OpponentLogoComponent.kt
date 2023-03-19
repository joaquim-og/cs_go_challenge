package com.confradestech.csgochallenge.presentation.components

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
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
import com.confradestech.csgochallenge.dataSources.models.Opponent
import com.confradestech.csgochallenge.presentation.previewAssets.opponent1
import com.confradestech.csgochallenge.utilities.ui.theme.CsGoChallengeTheme
import com.confradestech.csgochallenge.utilities.ui.theme.colorText

@Composable
fun OpponentLogoComponent(opponent: Opponent?) {
    Column(
        Modifier.height(120.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {

        val isImageUrlMissing = opponent?.opponentDetails?.imageUrl.isNullOrEmpty()

        Column(
            Modifier.height(100.dp).width(80.dp),
        ) {
            if (isImageUrlMissing) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_broken_image_24),
                    contentDescription = stringResource(id = R.string.generic_logo_desc),
                    tint = colorText
                )
            } else {
                Image(
                    painter = rememberImagePainter(opponent?.opponentDetails?.imageUrl),
                    contentDescription = opponent?.opponentDetails?.slug,
                    contentScale = ContentScale.Inside
                )
            }
        }
        Text(
            fontSize = 12.sp,
            text = opponent?.opponentDetails?.name
                ?: stringResource(id = R.string.generic_team_name_desc),
            color = colorText
        )
    }
}

//region previews
@Composable
@Preview(group = "CS GO Challenge", uiMode = Configuration.UI_MODE_NIGHT_NO)
private fun OpponentLogoComponent_Light_Preview() {
    CsGoChallengeTheme {
        OpponentLogoComponent(opponent1)
    }
}

@Composable
@Preview(group = "CS GO Challenge", uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun OpponentLogoComponent_Dark_Preview() {
    CsGoChallengeTheme {
        OpponentLogoComponent(opponent1)
    }
}
//endregion previews