package com.rahim.coinnews.domain.useCase

import com.rahim.coinnews.core.utils.Errors
import com.rahim.coinnews.core.utils.Resource
import com.rahim.coinnews.domain.model.MarketDomain
import com.rahim.coinnews.domain.repository.HomeRepository
import kotlinx.collections.immutable.PersistentList
import kotlinx.coroutines.flow.Flow

class GetMarketsUseCase(private val homeRepository: HomeRepository) {
    operator fun invoke(): Flow<Resource<PersistentList<MarketDomain>, Errors>> =
        homeRepository.getMarkets()
}