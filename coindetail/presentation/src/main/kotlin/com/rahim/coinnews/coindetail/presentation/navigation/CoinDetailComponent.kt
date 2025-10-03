package com.rahim.coinnews.coindetail.presentation.navigation

import androidx.compose.runtime.Immutable
import com.rahim.coinnews.coindetail.presentation.model.MarketChartPresentationLayerCoinDetail
import com.rahim.coinnews.coindetail.presentation.model.MarketDetailPresentationLayerCoinDetail
import com.rahim.coinnews.coindetail.presentation.model.MarketPresentationLayerCoinDetail
import com.rahim.coinnews.core.utils.LoadableData
import com.rahim.coinnews.core.utils.UnidirectionalComponent

interface CoinDetailComponent :
    UnidirectionalComponent<CoinDetailComponent.Event, CoinDetailComponent.State> {

    @Immutable
    sealed class Event {
        data class SetMarket(val market: MarketPresentationLayerCoinDetail) : Event()
        data class GetMarketChart(val marketId: String) : Event()
        data class GetMarketDetail(val marketId: String) : Event()
        data class OnFavoriteClick(val market: MarketPresentationLayerCoinDetail) : Event()
    }

    @Immutable
    data class State(
        val market: LoadableData<MarketPresentationLayerCoinDetail> = LoadableData.Initial,
        val marketChart: LoadableData<MarketChartPresentationLayerCoinDetail> = LoadableData.Initial,
        val marketDetail: LoadableData<MarketDetailPresentationLayerCoinDetail> = LoadableData.Initial,
    )
}