package com.rahim.coinnews.core.db.favorite.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_table")
data class FavoriteEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val symbol: String,
    val currentPrice: Double,
    val priceChangePercentage24h: Double,
    val imageUrl: String,
)