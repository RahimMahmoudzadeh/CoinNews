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
    }

    @Immutable
    data class State(
        val marketChart: LoadableData<MarketChartPresentationLayerCoinDetail> = LoadableData.Initial,
        val marketDetail: LoadableData<MarketDetailPresentationLayerCoinDetail> = LoadableData.Initial,
    )
}