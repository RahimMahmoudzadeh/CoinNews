package com.rahim.coinnews.coindetail.data.repository

import android.util.Log
import com.rahim.coinnews.coindetail.data.api.CoinDetailApi
import com.rahim.coinnews.coindetail.data.mapper.toMarketChartDomain
import com.rahim.coinnews.coindetail.data.mapper.toMarketDetailDomain
import com.rahim.coinnews.coindetail.data.mapper.toMarketDomain
import com.rahim.coinnews.coindetail.domain.model.MarketChartDomain
import com.rahim.coinnews.coindetail.domain.model.MarketDetailDomain
import com.rahim.coinnews.coindetail.domain.model.MarketDomain
import com.rahim.coinnews.coindetail.domain.repository.CoinDetailRepository
import com.rahim.coinnews.core.utils.Errors
import com.rahim.coinnews.core.utils.Resource
import com.rahim.coinnews.network.mapMessageStatusCode
import com.rahim.coinnews.network.onError
import com.rahim.coinnews.network.onException
import com.rahim.coinnews.network.statusCode
import com.rahim.coinnews.network.suspendMap
import com.rahim.coinnews.network.suspendOnError
import com.rahim.coinnews.network.suspendOnException
import com.rahim.coinnews.network.suspendOnSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CoinDetailRepositoryImpl(
    private val api: CoinDetailApi,
) : CoinDetailRepository {

//    override fun getFavoriteMarketList(): Flow<List<MarketDomain>> =
//        dao.getFavoriteMarketList().map { list -> list.map { it.toMarket() } }

    override suspend fun getMarketList(): Flow<Resource<List<MarketDomain>, Errors>> = flow {
        api.getMarkets(
            "usd",
            "market_cap_desc",
            20,
            1,
            false,
        ).suspendOnSuccess {
            val marketList = data.map { marketResponse ->
                marketResponse.toMarketDomain()
            }
            emit(Resource.Success(data = marketList))
        }.suspendOnError {
            suspendMap {
                emit(
                    Resource.Error(
                        Errors.ApiError(
                            it.statusCode.mapMessageStatusCode(),
                            it.statusCode.code
                        ),
                    ),
                )
            }        }.suspendOnException {
            suspendMap { emit(Resource.Error(Errors.ExceptionError(it.message, throwable))) }
        }
    }

//    override suspend fun toggleFavoriteMarket(oldMarket: MarketDomain): Flow<List<MarketDomain>> {
//        val marketEntity = oldMarket.copy(isFavorite = !oldMarket.isFavorite)
////        dao.insertMarket(marketEntity)
//    }

    override fun fetchChart(id: String): Flow<Resource<MarketChartDomain, Errors>> = flow {
        val chart = api.getMarketChart(id, "usd", 1)
        chart.suspendOnSuccess {
            emit(Resource.Success(data.toMarketChartDomain()))
        }.suspendOnError {
            suspendMap {
                emit(
                    Resource.Error(
                        error = Errors.ApiError(
                            it.statusCode.mapMessageStatusCode(),
                            it.statusCode.code,
                        ),
                    ),
                )
            }
        }.suspendOnException {
            suspendMap { emit(Resource.Error(Errors.ExceptionError(it.message, throwable))) }
        }
    }

    override fun fetchDetail(id: String): Flow<Resource<MarketDetailDomain, Errors>> =
        flow {
            val detail = api.getMarketDetail(id)
            detail.suspendOnSuccess {
                suspendMap {
                    emit(Resource.Success(data.toMarketDetailDomain()))
                }
            }.suspendOnError {
                suspendMap {
                    emit(
                        Resource.Error(
                            Errors.ApiError(
                                it.statusCode.mapMessageStatusCode(),
                                it.statusCode.code
                            ),
                        ),
                    )
                }
            }.suspendOnException {
                suspendMap { emit(Resource.Error(Errors.ExceptionError(it.message, it.throwable))) }
            }
        }
}