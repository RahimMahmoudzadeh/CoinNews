package com.rahim.coinnews.coindetail.domain.repository

import com.rahim.coinnews.coindetail.domain.model.MarketChartDomain
import com.rahim.coinnews.coindetail.domain.model.MarketDetailDomain
import com.rahim.coinnews.coindetail.domain.model.MarketDomain
import com.rahim.coinnews.core.utils.Errors
import com.rahim.coinnews.core.utils.Resource
import kotlinx.coroutines.flow.Flow

interface CoinDetailRepository {
    suspend fun getMarketList(): Flow<Resource<List<MarketDomain>, Errors>>
    fun fetchChart(id: String): Flow<Resource<MarketChartDomain, Errors>>
    fun fetchDetail(id: String): Flow<Resource<MarketDetailDomain, Errors>>
}