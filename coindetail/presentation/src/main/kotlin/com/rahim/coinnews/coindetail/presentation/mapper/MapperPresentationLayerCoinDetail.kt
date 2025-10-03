package com.rahim.coinnews.coindetail.presentation.mapper

import com.rahim.coinnews.coindetail.domain.model.MarketChartDomain
import com.rahim.coinnews.coindetail.domain.model.MarketDetailDomain
import com.rahim.coinnews.coindetail.domain.model.MarketDomain
import com.rahim.coinnews.coindetail.presentation.model.MarketChartPresentationLayerCoinDetail
import com.rahim.coinnews.coindetail.presentation.model.MarketDetailPresentationLayerCoinDetail
import com.rahim.coinnews.coindetail.presentation.model.MarketPresentationLayerCoinDetail
import kotlinx.collections.immutable.toPersistentList

fun MarketDetailDomain.toMarketDetailPresentationLayerCoinDetail(): MarketDetailPresentationLayerCoinDetail =
    MarketDetailPresentationLayerCoinDetail(
        id = this.id,
        marketCapRank = this.marketCapRank,
        marketData = this.marketData?.toMarketDataPresentationLayerCoinDetail(),
        name = this.name,
    )

fun MarketDetailDomain.MarketDataDomain.toMarketDataPresentationLayerCoinDetail(): MarketDetailPresentationLayerCoinDetail.MarketDataPresentationLayerCoinDetail =
    MarketDetailPresentationLayerCoinDetail.MarketDataPresentationLayerCoinDetail(
        high24hUSD = this.high24hUSD,
        low24hUSD = this.low24hUSD,
        marketCapUSD = this.marketCapUSD,
        marketCapRank = this.marketCapRank
    )

fun MarketDetailPresentationLayerCoinDetail.toMarketDetailDomainLayerCoinDetail(): MarketDetailDomain =
    MarketDetailDomain(
        id = this.id,
        marketCapRank = this.marketCapRank,
        marketData = this.marketData?.toMarketDataDomainLayerCoinDetail(),
        name = this.name
    )

fun MarketDetailPresentationLayerCoinDetail.MarketDataPresentationLayerCoinDetail.toMarketDataDomainLayerCoinDetail(): MarketDetailDomain.MarketDataDomain =
    MarketDetailDomain.MarketDataDomain(
        high24hUSD = this.high24hUSD,
        low24hUSD = this.low24hUSD,
        marketCapUSD = this.marketCapUSD,
        marketCapRank = this.marketCapRank
    )

fun MarketChartDomain.toMarketChartPresentationLayerCoinDetail(): MarketChartPresentationLayerCoinDetail =
    MarketChartPresentationLayerCoinDetail(
        prices = this.prices.toPersistentList(),
    )

fun MarketPresentationLayerCoinDetail.toMarketDomainLayerCoinDetail(): MarketDomain =
    MarketDomain(
        id = this.id,
        name = this.name,
        symbol = this.symbol,
        currentPrice = this.currentPrice,
        priceChangePercentage24h = this.priceChangePercentage24h,
        imageUrl = this.imageUrl,
        isFavorite = this.isFavorite
    )
