package com.rahim.coinnews.home.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.rahim.coinnews.core.utils.LoadableComponent
import com.rahim.coinnews.core.utils.errorViewMapper
import com.rahim.coinnews.core.utils.extentian.roundToTwoDecimalPlaces
import com.rahim.coinnews.core.utils.isLoading
import com.rahim.coinnews.core.utils.use
import com.rahim.coinnews.library.designsystem.R
import com.rahim.coinnews.library.designsystem.component.EmptyStateAnimation
import com.rahim.coinnews.library.designsystem.component.refresh.PullRefreshIndicator
import com.rahim.coinnews.library.designsystem.component.refresh.pullRefresh
import com.rahim.coinnews.library.designsystem.component.refresh.rememberPullRefreshState
import com.rahim.coinnews.library.designsystem.preview.ThemePreviews
import com.rahim.coinnews.library.designsystem.theme.CoinNewsTheme
import com.rahim.coinnews.home.presentation.component.MarketItemUi
import com.rahim.coinnews.home.presentation.model.MarketPresentationLayer
import com.rahim.coinnews.home.presentation.navigation.HomeComponent
import com.rahim.coinnews.home.presentation.previewProvider.HomeStateProvider
import com.rahim.coinnews.library.designsystem.widget.ErrorView


@Composable
fun HomeScreenRoute(
    component: HomeComponent,
) {
    val (state, event) = use(component = component)

    HomeScreenScreen(
        state = state,
        onNavigateToDetailScreen = {
            event(HomeComponent.Event.OnNavigateDetailScreen(it.id))
        },
        showFavoriteList = false,
        onFavoriteClick = { market ->
            event.invoke(HomeComponent.Event.OnFavoriteClick(market = market))
        },
        onRefresh = {
            event.invoke(HomeComponent.Event.OnGetMarketList)
        },
    )
}

@Composable
internal fun HomeScreenScreen(
    state: HomeComponent.State,
    showFavoriteList: Boolean,
    onNavigateToDetailScreen: (market: MarketPresentationLayer) -> Unit,
    onFavoriteClick: (market: MarketPresentationLayer) -> Unit,
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
                                MarketListItem(
                                    modifier = Modifier.fillMaxWidth(),
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
            },
            error = { error ->
                ErrorView(errorMessage = errorViewMapper(error))
            },
        )
    }
}

@Composable
fun MarketListItem(
    modifier: Modifier,
    market: MarketPresentationLayer,
    showFavoriteList: Boolean,
    onItemClick: () -> Unit,
    onFavoriteClick: () -> Unit,
) {
    with(market) {
        MarketItemUi(
            modifier = modifier,
            name = name,
            symbol = symbol,
            urlToImage = imageUrl,
            price = currentPrice.toString(),
            priceChangePercentage24h = priceChangePercentage24h.roundToTwoDecimalPlaces(),
            isFavorite = isFavorite,
            showFavoriteList = showFavoriteList,
            onItemClick = onItemClick,
            onFavoriteClick = onFavoriteClick,
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