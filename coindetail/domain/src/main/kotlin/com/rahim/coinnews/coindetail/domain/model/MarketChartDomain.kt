package com.rahim.coinnews.coindetail.domain.model

data class MarketChartDomain(
    val prices: List<Pair<Long, Double>>,
)
