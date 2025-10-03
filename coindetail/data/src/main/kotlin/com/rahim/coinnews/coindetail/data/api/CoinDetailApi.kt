package com.rahim.coinnews.coindetail.data.api

import com.rahim.coinnews.coindetail.data.model.MarketResponse
import com.rahim.coinnews.network.ApiResponse
import com.rahim.coinnews.coindetail.data.model.MarketChartResponse
import com.rahim.coinnews.coindetail.data.model.MarketDetailResponse

interface CoinDetailApi {
    suspend fun getMarkets(
        currency: String,
        order: String,
        perPage: Int,
        page: Int,
        sparkline: Boolean,
    ): ApiResponse<List<MarketResponse>>

    suspend fun getMarketChart(
        id: String,
        currency: String,
        days: Int,
    ): ApiResponse<MarketChartResponse>

    suspend fun getMarketDetail(id: String): ApiResponse<MarketDetailResponse>
}
