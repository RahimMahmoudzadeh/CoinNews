package com.rahim.coinnews.home.data.api

import com.rahim.coinnews.home.data.dto.MarketResponse
import com.rahim.coinnews.network.ApiResponse

interface MarketsApi {
    suspend fun getMarkets(
        currency: String,
        order: String,
        perPage: Int,
        page: Int,
        sparkline: Boolean,
    ): ApiResponse<List<MarketResponse>>
}
