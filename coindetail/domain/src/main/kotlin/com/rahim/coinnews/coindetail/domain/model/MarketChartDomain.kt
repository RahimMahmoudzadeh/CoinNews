package com.rahim.coinnews.coindetail.domain.model

import kotlinx.collections.immutable.PersistentList

data class MarketChartDomain(
    val prices: PersistentList<Pair<Long, Double>>,
)
