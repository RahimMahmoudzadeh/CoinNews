package com.rahim.coinnews.favorite.presentation.mapper

import com.rahim.coinnews.favorite.domain.model.MarketDomain
import com.rahim.coinnews.favorite.presentation.model.FavoriteCoinPresentationLayer

fun MarketDomain.toFavoriteCoinPresentationLayer(): FavoriteCoinPresentationLayer =
    FavoriteCoinPresentationLayer(
        id = this.id,
        name = this.name,
        symbol = this.symbol,
        currentPrice = this.currentPrice,
        priceChangePercentage24h = this.priceChangePercentage24h,
        imageUrl = this.imageUrl
    )