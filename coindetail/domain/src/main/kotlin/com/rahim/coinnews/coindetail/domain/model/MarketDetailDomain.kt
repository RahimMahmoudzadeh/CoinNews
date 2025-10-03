package com.rahim.coinnews.coindetail.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MarketDetailDomain(
    val id: String,
    val marketCapRank: Int,
    val marketData: MarketDataDomain?,
    val name: String,
) : Parcelable {
    @Parcelize
    data class MarketDataDomain(
        val high24hUSD: Double,
        val low24hUSD: Double,
        val marketCapUSD: Long,
        val marketCapRank: Int,
    ) : Parcelable
}
