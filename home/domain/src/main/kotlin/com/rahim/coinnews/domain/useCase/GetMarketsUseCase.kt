package com.rahim.coinnews.domain.useCase

import com.rahim.coinnews.core.utils.Errors
import com.rahim.coinnews.core.utils.Resource
import com.rahim.coinnews.domain.model.MarketDomainLayer
import com.rahim.coinnews.domain.repository.HomeRepository
import kotlinx.collections.immutable.PersistentList
import kotlinx.coroutines.flow.Flow

class GetMarketsUseCase(private val homeRepository: HomeRepository) {
    suspend operator fun invoke(): Flow<Resource<PersistentList<MarketDomainLayer>, Errors>> =
        homeRepository.getMarkets()
}