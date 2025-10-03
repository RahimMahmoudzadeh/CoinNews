package com.rahim.coinnews.coindetail.presentation.model

import kotlinx.collections.immutable.PersistentList

data class MarketChartPresentationLayerCoinDetail(
    val prices: PersistentList<Pair<Long, Double>>,
)
