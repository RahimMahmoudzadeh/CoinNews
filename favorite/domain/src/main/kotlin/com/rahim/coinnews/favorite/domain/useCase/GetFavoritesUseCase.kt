package com.rahim.coinnews.favorite.domain.useCase

import com.rahim.coinnews.favorite.domain.model.MarketDomain
import com.rahim.coinnews.favorite.domain.repo.FavoriteRepository
import kotlinx.coroutines.flow.Flow

class GetFavoritesUseCase(
    private val repository: FavoriteRepository,
) {
    operator fun invoke(): Flow<List<MarketDomain>> = repository.getFavoriteMarketList()
}
