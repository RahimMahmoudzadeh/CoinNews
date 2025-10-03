package com.rahim.coinnews.coindetail.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MarketDetailResponse(
    @SerialName("id")
    val id: String?,
    @SerialName("market_cap_rank")
    val marketCapRank: Int?,
    @SerialName("market_data")
    val marketData: MarketData?,
    @SerialName("name")
    val name: String?,
) {

    @Serializable
    data class MarketData(
        @SerialName("high_24h")
        val high24h: High24h?,
        @SerialName("low_24h")
        val low24h: Low24h?,
        @SerialName("market_cap")
        val marketCap: MarketCap?,
        @SerialName("market_cap_rank")
        val marketCapRank: Int?,
    ) {
        @Serializable
        data class High24h(
            @SerialName("usd")
            val usd: Double?,
        )

        @Serializable
        data class Low24h(
            @SerialName("usd")
            val usd: Double?,
        )

        @Serializable
        data class MarketCap(
            @SerialName("usd")
            val usd: Long?,
        )
    }
}
