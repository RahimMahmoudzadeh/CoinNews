package com.rahim.coinnews.coindetail.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MarketDetailDomainLayerCoinDetail(
    val id: String,
    val marketCapRank: Int,
    val marketData: MarketDataDomainLayerCoinDetail?,
    val name: String,
) : Parcelable {
    @Parcelize
    data class MarketDataDomainLayerCoinDetail(
        val high24hUSD: Double,
        val low24hUSD: Double,
        val marketCapUSD: Long,
        val marketCapRank: Int,
    ) : Parcelable
}
