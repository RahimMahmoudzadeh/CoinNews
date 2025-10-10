package com.rahim.coinnews.domain.repository

import com.rahim.coinnews.core.utils.Errors
import com.rahim.coinnews.core.utils.Resource
import com.rahim.coinnews.domain.model.MarketDomain
import kotlinx.collections.immutable.PersistentList
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    fun getMarkets(): Flow<Resource<List<MarketDomain>, Errors>>
    suspend fun saveFavoriteMarket(marketDomain: MarketDomain)
    suspend fun deleteFavoriteMarket(marketDomain: MarketDomain)
}