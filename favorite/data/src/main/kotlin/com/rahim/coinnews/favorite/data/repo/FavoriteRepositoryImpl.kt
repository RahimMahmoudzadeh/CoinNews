package com.rahim.coinnews.favorite.data.repo

import com.rahim.coinnews.core.db.favorite.dao.FavoriteDao
import com.rahim.coinnews.favorite.data.mapper.toFavoriteEntity
import com.rahim.coinnews.favorite.data.mapper.toMarketDomain
import com.rahim.coinnews.favorite.domain.model.MarketDomain
import com.rahim.coinnews.favorite.domain.repo.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FavoriteRepositoryImpl(private val favoriteDao: FavoriteDao) : FavoriteRepository {
    override fun getFavoriteMarketList(): Flow<List<MarketDomain>> =
        favoriteDao.getFavoriteMarketList()
            .map { favoritesEntity -> favoritesEntity.map { favoriteEntity -> favoriteEntity.toMarketDomain() } }

    override suspend fun toggleFavoriteMarket(marketDomain: MarketDomain) {
        val favoriteEntity = marketDomain.toFavoriteEntity()
        favoriteDao.insertMarket(favoriteEntity)
    }
}