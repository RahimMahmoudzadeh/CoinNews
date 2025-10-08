package com.rahim.coinnews.favorite.presentation.model

data class FavoriteCoinPresentationLayer(
    val id: String,
    val name: String,
    val symbol: String,
    val currentPrice: Double,
    val priceChangePercentage24h: Double,
    val imageUrl: String,
    val isFavorite: Boolean = false,
)