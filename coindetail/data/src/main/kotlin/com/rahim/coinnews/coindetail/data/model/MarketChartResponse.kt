package com.rahim.coinnews.coindetail.data.model

import kotlinx.serialization.Serializable

@Serializable
data class MarketChartResponse(
    val prices: List<List<Double>>,
)
