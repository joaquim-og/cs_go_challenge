package com.confradestech.csgochallenge.presentation.home.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.confradestech.csgochallenge.R
import com.confradestech.csgochallenge.dataSources.models.Game
import com.confradestech.csgochallenge.dataSources.models.dataStates.matchesListState
import com.confradestech.csgochallenge.dataSources.response.MatchesItem
import com.confradestech.csgochallenge.presentation.components.GenericErrorComponent
import com.confradestech.csgochallenge.presentation.components.NetworkErrorComponent
import com.confradestech.csgochallenge.presentation.components.TeamsMatchesCard
import com.confradestech.csgochallenge.ui.theme.colorText
import com.confradestech.csgochallenge.utilities.extensions.convertTimestampToPrettyDate
import com.confradestech.csgochallenge.utilities.extensions.exceptions.postException
import com.confradestech.csgochallenge.utilities.extensions.isOnline
import com.confradestech.csgochallenge.utilities.extensions.isScrolledToTheEnd
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun HomeScreen(
    matchesListState: matchesListState,
    onCardClicked: (MatchesItem?, Game?) -> Unit,
    onSwipeAction: () -> Unit,
    onLoadMoreItems: () -> Unit,
) {

    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        val isSwipeRefreshing = remember { mutableStateOf(false) }
        val stateSwipeToRefresh = rememberSwipeRefreshState(isSwipeRefreshing.value)
        Text(
            modifier = Modifier.padding(start = 20.dp),
            fontWeight = FontWeight.ExtraBold,
            fontSize = 40.sp,
            text = stringResource(R.string.home_title),
            color = colorText
        )

        if (LocalContext.current.isOnline()) {
            if (matchesListState.isLoading) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    CircularProgressIndicator()
                }
            } else if (matchesListState.isError) {
                GenericErrorComponent()
            } else {
                AnimatedVisibility(
                    visible = true,
                    enter = expandVertically() + fadeIn(),
                    exit = shrinkVertically() + fadeOut()
                ) {
                    SwipeRefresh(
                        state = stateSwipeToRefresh,
                        onRefresh = { onSwipeAction() }
                    ) {
                        Column(modifier = Modifier.fillMaxSize()) {
                            buildCardsContent(
                                matchesListState = matchesListState,
                                onCardClicked = onCardClicked,
                                onLoadMoreItems = onLoadMoreItems
                            )
                        }
                    }
                }
            }
        } else {
            Column(modifier = Modifier.fillMaxSize()) {
                NetworkErrorComponent()
            }
        }
    }
}

@Composable
fun buildCardsContent(
    matchesListState: matchesListState,
    onCardClicked: (MatchesItem?, Game?) -> Unit,
    onLoadMoreItems: () -> Unit
) {

    val runningGameFlag = stringResource(id = R.string.match_status_running_flag)
    val listState = rememberLazyListState()
    val originalMatchSize = remember {
        mutableStateOf(matchesListState.matches?.size?.minus(1))
    }

    LazyColumn(
        state = listState,
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        matchesListState.matches?.let { matches ->

            matches.forEach { match ->

                if (listState.isScrolledToTheEnd() && originalMatchSize.value == matches.size?.minus(
                        1
                    )
                ) {
                    onLoadMoreItems()
                    originalMatchSize.value = matches.size
                }

                val orderedGames =
                    match?.games?.sortedByDescending { it.status == runningGameFlag }

                orderedGames?.forEach { game ->
                    item {
                        val matchTime = if (game.beginAt.isNullOrEmpty()) {
                            match.beginAt
                        } else {
                            game.beginAt
                        }

                        val opponent1 = try {
                            match.opponents?.get(0)
                        } catch (error: Exception) {
                            error.postException()
                            null
                        }

                        val opponent2 = try {
                            match.opponents?.get(1)
                        } catch (error: Exception) {
                            error.postException()
                            null
                        }

                        Spacer(modifier = Modifier.height(10.dp))
                        TeamsMatchesCard(
                            matchTime = matchTime?.convertTimestampToPrettyDate(),
                            matchStatus = game.status,
                            opponent1 = opponent1,
                            opponent2 = opponent2,
                            league = match.league,
                            serie = match.serie,
                            onclick = { onCardClicked(match, game) },
                        )
                    }
                }
            }
        }

    }
}
