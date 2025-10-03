package com.rahim.coinnews.coindetail.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import com.rahim.coinnews.coindetail.presentation.component.MarketData
import com.rahim.coinnews.coindetail.presentation.component.QuadLineChart
import com.rahim.coinnews.coindetail.presentation.navigation.CoinDetailComponent
import com.rahim.coinnews.coindetail.presentation.model.MarketPresentationLayerCoinDetail
import com.rahim.coinnews.coindetail.presentation.previewProvider.MarketDetailStateProvider
import com.rahim.coinnews.core.utils.LoadableComponent
import com.rahim.coinnews.core.utils.LoadableData
import com.rahim.coinnews.core.utils.use
import com.rahim.coinnews.library.designsystem.component.FavoriteIcon
import com.rahim.coinnews.library.designsystem.component.shimmerEffect
import com.rahim.coinnews.library.designsystem.preview.ThemePreviews
import com.rahim.coinnews.library.designsystem.theme.CoinNewsTheme

@Composable
fun CoinDetailRoute(
    component: CoinDetailComponent,
) {
    val (state, event) = use(component = component)

//    LaunchedEffect(key1 = market) {
//        event.invoke(MarketDetailContract.Event.SetMarket(market = market))
//        // TODO: Move into viewModel?
//        event.invoke(MarketDetailContract.Event.GetMarketChart(marketId = market.id))
//        event.invoke(MarketDetailContract.Event.GetMarketDetail(marketId = market.id))
//    }

    CoinDetailScreen(
        marketDetailState = state,
        onFavoriteClick = {
            event.invoke(CoinDetailComponent.Event.OnFavoriteClick(market = it))
        },
    )
}

@Composable
private fun CoinDetailScreen(
    modifier: Modifier = Modifier,
    marketDetailState: CoinDetailComponent.State,
    onFavoriteClick: (market: MarketPresentationLayerCoinDetail) -> Unit,
) {
    Box(
        modifier = modifier.fillMaxSize(),
    ) {
        Column(
            modifier = modifier
                .fillMaxSize(),
        ) {
            TopAppBar(
                market = marketDetailState.market,
            )
            QuadLineChart(
                loadableData = marketDetailState.marketChart
            )
            MarketData(
                loadableData = marketDetailState.marketDetail,
            )
        }
        MarketDetailFloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            loadableData = marketDetailState.market,
            onFavoriteClick = onFavoriteClick,
        )
    }
}

@Composable
private fun MarketDetailFloatingActionButton(
    modifier: Modifier = Modifier,
    loadableData: LoadableData<MarketPresentationLayerCoinDetail>,
    onFavoriteClick: (market: MarketPresentationLayerCoinDetail) -> Unit,
) {
    LoadableComponent(
        loadableData = loadableData,
        loading = {},
        loaded = { data ->
            FloatingActionButton(
                modifier = modifier,
                onClick = {},
            ) {
                FavoriteIcon(isFavorite = data.isFavorite) {
                    onFavoriteClick(data)
                }
            }
        },
        error = { error -> },
    )
}

@Composable
private fun TopAppBar(
    market: LoadableData<MarketPresentationLayerCoinDetail>,
) {
    LoadableComponent(
        loadableData = market,
        loaded = { data ->
            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(model = data.imageUrl),
                        contentDescription = data.name,
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape),
                    )
                    Column(
                        modifier = Modifier.weight(1F),
                    ) {
                        Text(
                            text = data.name,
                            style = MaterialTheme.typography.headlineSmall,
                        )
                        Text(
                            text = "${data.currentPrice} $",
                            style = MaterialTheme.typography.bodyLarge,
                        )
                    }
                }
            }
        },
        loading = {
            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                            .shimmerEffect(),
                    )
                    Column(
                        modifier = Modifier.weight(1F),
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(0.5f)
                                .clip(RoundedCornerShape(4.dp))
                                .height(20.dp)
                                .shimmerEffect(),
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(0.2f)
                                .clip(RoundedCornerShape(4.dp))
                                .height(12.dp)
                                .shimmerEffect(),
                        )
                    }
                }
            }
        },
        error = {
        },
    )
}

// TODO: Move from UI Layer to domain

fun formatNumber(number: Long?): String {
    val format = number?.div(BILLION)
    if (format != null) {
        return if (format >= 1) {
            "$${format}B"
        } else {
            "$${format}M"
        }
    }
    return ""
}

@ThemePreviews
@Composable
private fun CoinDetailScreenPrev(
    @PreviewParameter(MarketDetailStateProvider::class) marketDetailState: State,
) {
    CoinNewsTheme {
        CoinDetailScreen(
            marketDetailState = marketDetailState,
            onFavoriteClick = {},
        )
    }
}

private const val BILLION: Long = 1000000000L
