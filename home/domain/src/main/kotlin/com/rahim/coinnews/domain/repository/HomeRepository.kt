package com.rahim.coinnews.domain.repository

import com.rahim.coinnews.core.utils.Errors
import com.rahim.coinnews.core.utils.Resource
import com.rahim.coinnews.domain.model.MarketDomainLayer
import kotlinx.collections.immutable.PersistentList
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    suspend fun getMarkets(): Flow<Resource<PersistentList<MarketDomainLayer>, Errors>>
}