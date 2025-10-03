package com.rahim.coinnews.coindetail.data.api

import com.rahim.coinnews.coindetail.data.model.MarketChartResponse
import com.rahim.coinnews.coindetail.data.model.MarketDetailResponse
import com.rahim.coinnews.coindetail.data.model.MarketResponse
import com.rahim.coinnews.network.ApiResponse
import com.rahim.coinnews.network.HttpRoutes.COINS
import com.rahim.coinnews.network.HttpRoutes.DAYS
import com.rahim.coinnews.network.HttpRoutes.MARKETS
import com.rahim.coinnews.network.HttpRoutes.MARKET_CHART
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

class CoinDetailApiImpl(
    private val httpClient: HttpClient,
) : CoinDetailApi {
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

    override suspend fun getMarketChart(
        id: String,
        currency: String,
        days: Int,
    ): ApiResponse<MarketChartResponse> = withContext(Dispatchers.IO) {
        val response = httpClient.get<MarketChartResponse> {
            url {
                appendPathSegments(COINS, id, MARKET_CHART)
                parameters.append(VS_CURRENCY, currency)
                parameters.append(DAYS, days.toString())
            }
        }
        response
    }

    override suspend fun getMarketDetail(id: String): ApiResponse<MarketDetailResponse> =
        withContext(Dispatchers.IO) {
            val response = httpClient.get<MarketDetailResponse> {
                url {
                    appendPathSegments(COINS, id)
                }
            }
            response
        }
}
