package com.rahim.coinnews.domain.useCase

import com.rahim.coinnews.domain.model.MarketDomain
import com.rahim.coinnews.domain.repository.HomeRepository

open class SaveFavoriteUseCase(
    private val homeRepository: HomeRepository,
) {
    open suspend operator fun invoke(market: MarketDomain) = homeRepository.saveFavoriteMarket(market)
}