package com.rahim.coinnews.coindetail.presentation.previewProvider

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.rahim.coinnews.coindetail.presentation.model.MarketChartPresentationLayerCoinDetail
import com.rahim.coinnews.coindetail.presentation.model.MarketDetailPresentationLayerCoinDetail
import com.rahim.coinnews.coindetail.presentation.model.MarketPresentationLayerCoinDetail
import com.rahim.coinnews.coindetail.presentation.navigation.CoinDetailComponent
import com.rahim.coinnews.core.utils.Errors
import com.rahim.coinnews.core.utils.LoadableData
import kotlinx.collections.immutable.persistentListOf

private const val MARKET_LIST_COUNT = 10

val marketList = List(MARKET_LIST_COUNT) { index ->
    market().copy(
        name = "name $index",
    )
}

private fun market() = MarketPresentationLayerCoinDetail(
    id = "id",
    name = "name 1",
    symbol = "symbol",
    currentPrice = 100000.0,
    priceChangePercentage24h = 100000.0,
    imageUrl = "some_shit_url.png",
)
class CoinDetailStateProvider : PreviewParameterProvider<CoinDetailComponent.State> {
    override val values: Sequence<CoinDetailComponent.State> = sequenceOf(
        CoinDetailComponent.State(
            market = LoadableData.Loading,
            marketChart = LoadableData.Loading,
            marketDetail = LoadableData.Loading,
        ),
        CoinDetailComponent.State(
            market = LoadableData.Loaded(data = marketList[0]),
            marketChart = LoadableData.Loaded(
                data = MarketChartPresentationLayerCoinDetail(
                    prices = persistentListOf(
                        -1000L to 45000.0,
                        -500L to 46000.5,
                    ),
                ),
            ),
            marketDetail = LoadableData.Loaded(
                data = MarketDetailPresentationLayerCoinDetail(
                    id = "1",
                    marketCapRank = 2,
                    name = "name",
                    marketData = null,
                ),
            ),
        ),
        CoinDetailComponent.State(
            market = LoadableData.Error(
                error = Errors.ExceptionError(
                    message = "some exception",
                    throwable = Throwable("some exception"),
                ),
            ),
            marketChart = LoadableData.Error(
                error = Errors.ExceptionError(
                    message = "some exception",
                    throwable = Throwable("some exception"),
                ),
            ),
            marketDetail = LoadableData.Error(
                error = Errors.ExceptionError(
                    message = "some exception",
                    throwable = Throwable("some exception"),
                ),
            ),
        ),
    )
}
