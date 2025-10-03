package com.rahim.coinnews.coindetail.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MarketDetailPresentationLayerCoinDetail(
    val id: String,
    val marketCapRank: Int,
    val marketData: MarketDataPresentationLayerCoinDetail?,
    val name: String,
) : Parcelable {
    @Parcelize
    data class MarketDataPresentationLayerCoinDetail(
        val high24hUSD: Double,
        val low24hUSD: Double,
        val marketCapUSD: Long,
        val marketCapRank: Int,
    ) : Parcelable
}
