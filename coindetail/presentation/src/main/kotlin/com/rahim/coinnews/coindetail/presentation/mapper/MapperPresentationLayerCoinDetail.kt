package com.rahim.coinnews.coindetail.presentation.mapper

import com.rahim.coinnews.coindetail.domain.model.MarketChartDomainLayerCoinDetail
import com.rahim.coinnews.coindetail.domain.model.MarketDetailDomainLayerCoinDetail
import com.rahim.coinnews.coindetail.domain.model.MarketDomainLayerCoinDetail
import com.rahim.coinnews.coindetail.presentation.model.MarketChartPresentationLayerCoinDetail
import com.rahim.coinnews.coindetail.presentation.model.MarketDetailPresentationLayerCoinDetail
import com.rahim.coinnews.coindetail.presentation.model.MarketPresentationLayerCoinDetail

fun MarketDetailDomainLayerCoinDetail.toMarketDetailPresentationLayerCoinDetail(): MarketDetailPresentationLayerCoinDetail =
    MarketDetailPresentationLayerCoinDetail(
        id = this.id,
        marketCapRank = this.marketCapRank,
        marketData = this.marketData?.toMarketDataPresentationLayerCoinDetail(),
        name = this.name,
    )

fun MarketDetailDomainLayerCoinDetail.MarketDataDomainLayerCoinDetail.toMarketDataPresentationLayerCoinDetail(): MarketDetailPresentationLayerCoinDetail.MarketDataPresentationLayerCoinDetail =
    MarketDetailPresentationLayerCoinDetail.MarketDataPresentationLayerCoinDetail(
        high24hUSD = this.high24hUSD,
        low24hUSD = this.low24hUSD,
        marketCapUSD = this.marketCapUSD,
        marketCapRank = this.marketCapRank
    )

fun MarketDetailPresentationLayerCoinDetail.toMarketDetailDomainLayerCoinDetail(): MarketDetailDomainLayerCoinDetail =
    MarketDetailDomainLayerCoinDetail(
        id = this.id,
        marketCapRank = this.marketCapRank,
        marketData = this.marketData?.toMarketDataDomainLayerCoinDetail(),
        name = this.name
    )

fun MarketDetailPresentationLayerCoinDetail.MarketDataPresentationLayerCoinDetail.toMarketDataDomainLayerCoinDetail(): MarketDetailDomainLayerCoinDetail.MarketDataDomainLayerCoinDetail =
    MarketDetailDomainLayerCoinDetail.MarketDataDomainLayerCoinDetail(
        high24hUSD = this.high24hUSD,
        low24hUSD = this.low24hUSD,
        marketCapUSD = this.marketCapUSD,
        marketCapRank = this.marketCapRank
    )

fun MarketChartDomainLayerCoinDetail.toMarketChartPresentationLayerCoinDetail(): MarketChartPresentationLayerCoinDetail =
    MarketChartPresentationLayerCoinDetail(
        prices = this.prices,
    )

fun MarketPresentationLayerCoinDetail.toMarketDomainLayerCoinDetail(): MarketDomainLayerCoinDetail =
    MarketDomainLayerCoinDetail(
        id = this.id,
        name = this.name,
        symbol = this.symbol,
        currentPrice = this.currentPrice,
        priceChangePercentage24h = this.priceChangePercentage24h,
        imageUrl = this.imageUrl,
        isFavorite = this.isFavorite
    )
