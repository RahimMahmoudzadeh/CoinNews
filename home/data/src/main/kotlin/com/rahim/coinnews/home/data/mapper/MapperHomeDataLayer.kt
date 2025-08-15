package com.rahim.coinnews.home.data.mapper

import com.rahim.coinnews.domain.model.MarketDomainLayer
import com.rahim.coinnews.home.data.dto.MarketResponse

fun MarketResponse.toMarketDomainLayer(): MarketDomainLayer {
    return MarketDomainLayer(
        id = this.id,
        name = this.name,
        symbol = this.symbol,
        currentPrice = this.currentPrice,
        priceChangePercentage24h = this.priceChangePercentage24h,
        imageUrl = this.imageUrl,
    )
}