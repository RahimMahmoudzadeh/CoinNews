package com.rahim.coinnews.favorite.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class MarketDomain(
    val id: String,
    val name: String,
    val symbol: String,
    val currentPrice: Double,
    val priceChangePercentage24h: Double,
    val imageUrl: String,
)
