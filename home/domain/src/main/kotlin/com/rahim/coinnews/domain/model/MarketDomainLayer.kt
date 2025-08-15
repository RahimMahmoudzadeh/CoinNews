package com.rahim.coinnews.domain.model

data class MarketDomainLayer(
    val id: String,
    val name: String,
    val symbol: String,
    val currentPrice: Double,
    val priceChangePercentage24h: Double,
    val imageUrl: String,
    val isFavorite: Boolean = false,
)