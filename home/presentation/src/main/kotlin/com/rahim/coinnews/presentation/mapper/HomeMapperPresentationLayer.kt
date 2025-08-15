package com.rahim.coinnews.presentation.mapper

import com.rahim.coinnews.domain.model.MarketDomainLayer
import com.rahim.coinnews.presentation.model.MarketPresentationLayer

fun MarketDomainLayer.toMarketPresentationLayer(): MarketPresentationLayer =
    MarketPresentationLayer(
        id = this.id,
        name = this.name,
        symbol = this.symbol,
        currentPrice = this.currentPrice,
        priceChangePercentage24h = this.priceChangePercentage24h,
        imageUrl = this.imageUrl
    )