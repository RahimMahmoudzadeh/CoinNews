package com.rahim.coinnews.home.presentation.mapper

import com.rahim.coinnews.domain.model.MarketDomain
import com.rahim.coinnews.home.presentation.model.MarketPresentationLayer

fun MarketDomain.toMarketPresentationLayer(): MarketPresentationLayer =
    MarketPresentationLayer(
        id = this.id,
        name = this.name,
        symbol = this.symbol,
        currentPrice = this.currentPrice,
        isFavorite = this.isFavorite,
        priceChangePercentage24h = this.priceChangePercentage24h,
        imageUrl = this.imageUrl
    )

fun MarketPresentationLayer.toMarketDomain(): MarketDomain = MarketDomain(
    id = this.id,
    name = this.name,
    symbol = this.symbol,
    currentPrice = this.currentPrice,
    priceChangePercentage24h = this.priceChangePercentage24h,
    imageUrl = this.imageUrl
)