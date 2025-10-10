package com.rahim.coinnews.coindetail.data.mapper

import com.rahim.coinnews.coindetail.data.model.MarketChartResponse
import com.rahim.coinnews.coindetail.data.model.MarketDetailResponse
import com.rahim.coinnews.coindetail.data.model.MarketResponse
import com.rahim.coinnews.coindetail.domain.model.MarketChartDomain
import com.rahim.coinnews.coindetail.domain.model.MarketDetailDomain
import com.rahim.coinnews.coindetail.domain.model.MarketDomain
import com.rahim.coinnews.core.db.favorite.model.FavoriteEntity
import kotlin.collections.get
import kotlin.text.toLong

fun MarketDetailResponse.toMarketDetailDomain(): MarketDetailDomain =
    MarketDetailDomain(
        id = this.id ?: "",
        name = this.name ?: "",
        marketCapRank = this.marketCapRank ?: 0,
        marketData = this.marketData?.toMarketDataDomain()
    )

fun MarketDetailResponse.MarketData.toMarketDataDomain(): MarketDetailDomain.MarketDataDomain =
    MarketDetailDomain.MarketDataDomain(
        high24hUSD = this.high24h?.usd ?: 0.0,
        low24hUSD = this.low24h?.usd ?: 0.0,
        marketCapUSD = this.marketCap?.usd ?: 0L,
        marketCapRank = this.marketCapRank ?: 0
    )

fun MarketChartResponse.toMarketChartDomain(): MarketChartDomain =
    MarketChartDomain(prices = this.prices.map { element -> Pair(element[0].toLong(), element[1]) })

fun MarketResponse.toMarketDomain(): MarketDomain = MarketDomain(
    id = this.id,
    name = this.name,
    symbol = this.symbol,
    currentPrice = this.currentPrice,
    priceChangePercentage24h = this.priceChangePercentage24h,
    imageUrl = this.imageUrl,
)

fun MarketDomain.toFavoriteEntity(): FavoriteEntity =
    FavoriteEntity(
        id = this.id,
        name = this.name,
        symbol = this.symbol,
        currentPrice = this.currentPrice,
        priceChangePercentage24h = this.priceChangePercentage24h,
        imageUrl = this.imageUrl
    )