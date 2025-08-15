package com.rahim.coinnews.home.data.api

import com.rahim.coinnews.home.data.dto.MarketResponse
import com.rahim.coinnews.network.ApiResponse
import com.rahim.coinnews.network.HttpRoutes.COINS
import com.rahim.coinnews.network.HttpRoutes.MARKETS
import com.rahim.coinnews.network.HttpRoutes.ORDER
import com.rahim.coinnews.network.HttpRoutes.PAGE
import com.rahim.coinnews.network.HttpRoutes.PER_PAGE
import com.rahim.coinnews.network.HttpRoutes.SPARKLINE
import com.rahim.coinnews.network.HttpRoutes.VS_CURRENCY
import com.rahim.coinnews.network.get
import io.ktor.client.HttpClient
import io.ktor.http.appendPathSegments
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HomeApiImpl(
    private val httpClient: HttpClient,
) : HomeApi {
    override suspend fun getMarkets(
        currency: String,
        order: String,
        perPage: Int,
        page: Int,
        sparkline: Boolean,
    ): ApiResponse<List<MarketResponse>> = withContext(Dispatchers.IO) {
        val response = httpClient.get<List<MarketResponse>> {
            url {
                appendPathSegments(COINS, MARKETS)
                parameters.append(VS_CURRENCY, currency)
                parameters.append(ORDER, order)
                parameters.append(PER_PAGE, perPage.toString())
                parameters.append(PAGE, page.toString())
                parameters.append(SPARKLINE, sparkline.toString())
            }
        }
        response
    }
}
