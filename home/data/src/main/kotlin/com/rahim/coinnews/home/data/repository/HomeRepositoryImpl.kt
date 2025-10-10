package com.rahim.coinnews.home.data.repository

import com.rahim.coinnews.core.db.favorite.dao.FavoriteDao
import com.rahim.coinnews.core.utils.Errors
import com.rahim.coinnews.core.utils.Resource
import com.rahim.coinnews.domain.model.MarketDomain
import com.rahim.coinnews.domain.repository.HomeRepository
import com.rahim.coinnews.home.data.api.HomeApi
import com.rahim.coinnews.home.data.mapper.toFavoriteEntity
import com.rahim.coinnews.home.data.mapper.toMarketDomain
import com.rahim.coinnews.network.statusCode
import com.rahim.coinnews.network.suspendMap
import com.rahim.coinnews.network.suspendOnError
import com.rahim.coinnews.network.suspendOnException
import com.rahim.coinnews.network.suspendOnSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class HomeRepositoryImpl(private val marketApi: HomeApi, private val favoriteDao: FavoriteDao) :
    HomeRepository {
    override fun getMarkets(): Flow<Resource<List<MarketDomain>, Errors>> =
        flow {
            marketApi.getMarkets(
                "usd",
                "market_cap_desc",
                20,
                1,
                false,
            ).suspendOnSuccess {
                val favoriteMarketIds = favoriteDao.getFavoriteMarkets()
                    .map { it.id }
                    .toSet()

                val marketEntityList = data.map { marketResponse ->
                    val marketDomain = marketResponse.toMarketDomain()
                    marketDomain.copy(isFavorite = marketDomain.id in favoriteMarketIds)
                }
                emit(Resource.Success(marketEntityList))
            }.suspendOnError {
                suspendMap {
                    emit(
                        Resource.Error(
                            Errors.ApiError(
                                message = it.message,
                                code = it.statusCode.code
                            )
                        )
                    )
                }
            }.suspendOnException {
                suspendMap {
                    emit(
                        Resource.Error(
                            Errors.ExceptionError(
                                message = message,
                                throwable = throwable
                            )
                        )
                    )
                }
            }
        }

    override suspend fun saveFavoriteMarket(marketDomain: MarketDomain) {
        favoriteDao.insertMarket(marketDomain.toFavoriteEntity())
    }

    override suspend fun deleteFavoriteMarket(marketDomain: MarketDomain) {
        favoriteDao.delete(marketDomain.toFavoriteEntity())
    }
}