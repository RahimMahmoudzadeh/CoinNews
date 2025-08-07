package com.rahim.coinnews.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.rahim.coinnews.library.designsystem.preview.ThemePreviews
import com.rahim.coinnews.library.designsystem.theme.CoinNewsTheme
import com.rahim.coinnews.presentation.navigation.HomeComponent
import com.rahim.coinnews.presentation.preview_provider.HomeStateProvider


@Composable
fun HomeScreenRoute(
    viewModel: MarketListViewModel = hiltViewModel(),
    showFavoriteList: Boolean = false,
    onNavigateToDetailScreen: (market: MarketModel) -> Unit,
) {
    val (state, event) = use(viewModel = viewModel)

    HomeScreenScreen(
        state = state,
        onNavigateToDetailScreen = onNavigateToDetailScreen,
        showFavoriteList = showFavoriteList,
        onFavoriteClick = { market ->
            event.invoke(MarketListContract.Event.OnFavoriteClick(market = market))
        },
        onRefresh = {
            event.invoke(MarketListContract.Event.OnGetMarketList)
        },
    )
}

@Composable
fun HomeScreenScreen(
    state: HomeComponent.State,
    showFavoriteList: Boolean,
    onNavigateToDetailScreen: (market: MarketModel) -> Unit,
    onFavoriteClick: (market: MarketModel) -> Unit,
    onRefresh: () -> Unit,
) {
    val refreshState = rememberPullRefreshState(
        refreshing = state.marketList.isLoading,
        onRefresh = onRefresh,
    )
    Box(
        modifier =
            Modifier
                .fillMaxWidth()
                .pullRefresh(refreshState),
    ) {
        PullRefreshIndicator(
            state.marketList.isLoading,
            refreshState,
            Modifier.align(Alignment.TopCenter),
        )
        LoadableComponent(
            loadableData = state.marketList,
            loaded = { data ->
                AnimatedVisibility(
                    visible = !state.marketList.isLoading,
                    enter = fadeIn(),
                    exit = fadeOut(),
                ) {
                    if (data.isEmpty() && state.showFavoriteList) {
                        EmptyStateAnimation(
                            lottieCompositionSpec = LottieCompositionSpec.RawRes(
                                R.raw.empty_state_animation,
                            ),
                        )
                    } else {
                        LazyColumn(modifier = Modifier.fillMaxWidth()) {
                            items(
                                items = data,
                                key = { it.name },
                            ) { market ->
                                Modifier
                                    .fillMaxWidth()
                                Column(
                                    modifier =
                                        Modifier.animateItem(
                                            placementSpec = tween(durationMillis = 250),
                                            fadeInSpec = null,
                                            fadeOutSpec = null,
                                        ),
                                ) {
                                    MarketListItem(
                                        modifier = Modifier,
                                        market = market,
                                        showFavoriteList = showFavoriteList,
                                        onItemClick = {
                                            onNavigateToDetailScreen(market)
                                        },
                                        onFavoriteClick = {
                                            onFavoriteClick(market)
                                        },
                                    )
                                }
                            }
                        }
                    }
                }
            },
            error = { error ->
                ErrorView(errorMessage = errorViewMapper(error))
            },
        )
    }
}

@ThemePreviews
@Composable
private fun MarketListScreenPrev(
    @PreviewParameter(HomeStateProvider::class)
    homeState: HomeComponent.State,
) {
    CoinNewsTheme {
        Surface {
            HomeScreenScreen(
                state = homeState,
                showFavoriteList = false,
                onNavigateToDetailScreen = {},
                onFavoriteClick = {},
                onRefresh = {},
            )
        }
    }
}