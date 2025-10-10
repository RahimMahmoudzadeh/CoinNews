package com.rahim.coinnews.favorite.domain.repo

import com.rahim.coinnews.favorite.domain.model.MarketDomain
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {
    fun getFavoriteMarketList(): Flow<List<MarketDomain>>
    suspend fun saveFavoriteMarket(marketDomain: MarketDomain)
}