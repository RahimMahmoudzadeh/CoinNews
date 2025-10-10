package com.rahim.coinnews.home.data.mapper

import com.rahim.coinnews.core.db.favorite.model.FavoriteEntity
import com.rahim.coinnews.domain.model.MarketDomain
import com.rahim.coinnews.home.data.dto.MarketResponse

fun MarketResponse.toMarketDomain(): MarketDomain {
    return MarketDomain(
        id = this.id,
        name = this.name,
        symbol = this.symbol,
        currentPrice = this.currentPrice,
        priceChangePercentage24h = this.priceChangePercentage24h,
        imageUrl = this.imageUrl,
    )
}

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