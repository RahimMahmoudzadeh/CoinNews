package com.rahim.coinnews.favorite.data.mapper

import com.rahim.coinnews.core.db.favorite.model.FavoriteEntity
import com.rahim.coinnews.favorite.domain.model.MarketDomain

fun MarketDomain.toFavoriteEntity(): FavoriteEntity =
    FavoriteEntity(
        id = this.id,
        name = this.name,
        symbol = this.symbol,
        currentPrice = this.currentPrice,
        priceChangePercentage24h = this.priceChangePercentage24h,
        imageUrl = this.imageUrl
    )

fun FavoriteEntity.toMarketDomain(): MarketDomain =
    MarketDomain(
        id = this.id,
        name = this.name,
        symbol = this.symbol,
        currentPrice = this.currentPrice,
        priceChangePercentage24h = this.priceChangePercentage24h,
        imageUrl = this.imageUrl
    )