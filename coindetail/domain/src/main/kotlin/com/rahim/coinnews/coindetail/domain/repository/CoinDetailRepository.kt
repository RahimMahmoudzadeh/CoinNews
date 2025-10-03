package com.rahim.coinnews.coindetail.domain.repository

import com.rahim.coinnews.coindetail.domain.model.MarketChartDomainLayerCoinDetail
import com.rahim.coinnews.coindetail.domain.model.MarketDetailDomainLayerCoinDetail
import com.rahim.coinnews.coindetail.domain.model.MarketDomainLayerCoinDetail
import com.rahim.coinnews.core.utils.Errors
import com.rahim.coinnews.core.utils.Resource
import kotlinx.coroutines.flow.Flow

interface CoinDetailRepository {
    fun getMarketList(): Flow<List<MarketDomainLayerCoinDetail>>
    fun getFavoriteMarketList(): Flow<List<MarketDomainLayerCoinDetail>>
    suspend fun syncMarketList()
    suspend fun toggleFavoriteMarket(oldMarket: MarketDomainLayerCoinDetail)
    fun fetchChart(id: String): Flow<Resource<MarketChartDomainLayerCoinDetail, Errors>>
    fun fetchDetail(id: String): Flow<Resource<MarketDetailDomainLayerCoinDetail, Errors>>
}