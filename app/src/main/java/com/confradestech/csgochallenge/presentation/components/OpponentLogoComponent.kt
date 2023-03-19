package com.confradestech.csgochallenge.presentation.components

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.confradestech.csgochallenge.dataSources.models.Opponent
import com.confradestech.csgochallenge.presentation.previewAssets.opponent1
import com.confradestech.csgochallenge.ui.theme.CsGoChallengeTheme
import com.confradestech.csgochallenge.ui.theme.colorText

@Composable
fun OpponentLogoComponent(opponent: Opponent?) {
    Column(
        Modifier.height(120.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Column(
            Modifier.height(100.dp),
        ) {
            Image(
                painter = rememberImagePainter(opponent?.opponentDetails?.imageUrl),
                contentDescription = opponent?.opponentDetails?.slug,
                contentScale = ContentScale.Inside
            )
        }
        Text(
            fontSize = 12.sp,
            text = opponent?.opponentDetails?.name ?: "",
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