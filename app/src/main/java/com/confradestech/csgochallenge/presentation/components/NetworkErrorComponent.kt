package com.confradestech.csgochallenge.presentation.components

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.confradestech.csgochallenge.ui.theme.CsGoChallengeTheme
import com.confradestech.csgochallenge.ui.theme.colorText
import com.confradestech.csgochallenge.ui.theme.errorIconColor

@Composable
fun NetworkErrorComponent() {
    Column(
        Modifier.fillMaxSize().padding(start = 20.dp, end = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_signal_wifi_connected_no_internet_4_24),
                contentDescription = stringResource(id = R.string.network_error),
                tint = errorIconColor
            )
        Text(
            fontSize = 12.sp,
            text = stringResource(id = R.string.network_error),
            color = colorText
        )
    }
}

//region previews
@Composable
@Preview(group = "CS GO Challenge", uiMode = Configuration.UI_MODE_NIGHT_NO)
private fun NetworkErrorComponent_Light_Preview() {
    CsGoChallengeTheme {
        NetworkErrorComponent()
    }
}

@Composable
@Preview(group = "CS GO Challenge", uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun NetworkErrorComponent_Dark_Preview() {
    CsGoChallengeTheme {
        NetworkErrorComponent()
    }
}
//endregion previews