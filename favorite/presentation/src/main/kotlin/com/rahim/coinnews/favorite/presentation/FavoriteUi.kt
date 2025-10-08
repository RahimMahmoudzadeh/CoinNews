package com.rahim.coinnews.favorite.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.rahim.coinnews.core.utils.LoadableComponent
import com.rahim.coinnews.core.utils.errorViewMapper
import com.rahim.coinnews.core.utils.extentian.roundToTwoDecimalPlaces
import com.rahim.coinnews.core.utils.isLoading
import com.rahim.coinnews.core.utils.use
import com.rahim.coinnews.favorite.presentation.component.FavoriteItemUi
import com.rahim.coinnews.favorite.presentation.model.FavoriteCoinPresentationLayer
import com.rahim.coinnews.favorite.presentation.navigation.FavoriteComponent
import com.rahim.coinnews.library.designsystem.R
import com.rahim.coinnews.library.designsystem.component.EmptyStateAnimation
import com.rahim.coinnews.library.designsystem.component.refresh.PullRefreshIndicator
import com.rahim.coinnews.library.designsystem.component.refresh.pullRefresh
import com.rahim.coinnews.library.designsystem.component.refresh.rememberPullRefreshState
import com.rahim.coinnews.library.designsystem.widget.ErrorView

@Composable
fun FavoriteRoot(modifier: Modifier = Modifier, component: FavoriteComponent) {
    val (state, event) = use(component)
    FavoriteScreen(
        modifier = modifier,
        state = state,
        onRefresh = {},
        onNavigateToDetailScreen = {},
        onFavoriteClick = {})
}

@Composable
private fun FavoriteScreen(
    modifier: Modifier = Modifier,
    state: FavoriteComponent.State,
    onRefresh: () -> Unit,
    onNavigateToDetailScreen: (FavoriteCoinPresentationLayer) -> Unit,
    onFavoriteClick: (FavoriteCoinPresentationLayer) -> Unit,
) {

    val refreshState = rememberPullRefreshState(
        refreshing = state.marketList.isLoading,
        onRefresh = onRefresh,
    )
    Box(
        modifier =
            modifier
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
                    if (data.isEmpty()) {
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
    market: FavoriteCoinPresentationLayer,
    onItemClick: () -> Unit,
    onFavoriteClick: () -> Unit,
) {
    with(market) {
        FavoriteItemUi(
            modifier = modifier,
            name = name,
            symbol = symbol,
            urlToImage = imageUrl,
            price = currentPrice.toString(),
            priceChangePercentage24h = priceChangePercentage24h.roundToTwoDecimalPlaces(),
            isFavorite = isFavorite,
            onItemClick = onItemClick,
            onFavoriteClick = onFavoriteClick,
        )
    }
}