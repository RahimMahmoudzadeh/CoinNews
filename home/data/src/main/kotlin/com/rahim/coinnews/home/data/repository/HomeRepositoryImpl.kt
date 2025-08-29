package com.rahim.coinnews.home.data.repository

import android.util.Log
import com.rahim.coinnews.core.utils.Errors
import com.rahim.coinnews.core.utils.Resource
import com.rahim.coinnews.domain.model.MarketDomainLayer
import com.rahim.coinnews.domain.repository.HomeRepository
import com.rahim.coinnews.home.data.api.HomeApi
import com.rahim.coinnews.home.data.mapper.toMarketDomainLayer
import com.rahim.coinnews.network.mapMessageStatusCode
import com.rahim.coinnews.network.onError
import com.rahim.coinnews.network.onException
import com.rahim.coinnews.network.statusCode
import com.rahim.coinnews.network.suspendMap
import com.rahim.coinnews.network.suspendOnError
import com.rahim.coinnews.network.suspendOnException
import com.rahim.coinnews.network.suspendOnSuccess
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import kotlin.code

class HomeRepositoryImpl(private val marketApi: HomeApi) : HomeRepository {
    override fun getMarkets(): Flow<Resource<PersistentList<MarketDomainLayer>, Errors>> =
        flow {
            marketApi.getMarkets(
                "usd",
                "market_cap_desc",
                20,
                1,
                false,
            ).suspendOnSuccess {
                val marketEntityList = data.map { marketResponse ->
                    marketResponse.toMarketDomainLayer()
                }
                emit(Resource.Success(marketEntityList.toPersistentList()))
            }.suspendOnError {
                suspendMap {
                    emit(Resource.Error(Errors.ApiError(message = it.message, code = it.statusCode.code)))
                }
            }.suspendOnException {
                suspendMap {
                    emit(Resource.Error(Errors.ExceptionError(message = message, throwable = throwable)))
                }
            }
        }
}