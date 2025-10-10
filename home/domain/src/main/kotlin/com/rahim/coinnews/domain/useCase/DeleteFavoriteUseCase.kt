package com.rahim.coinnews.domain.useCase

import com.rahim.coinnews.domain.model.MarketDomain
import com.rahim.coinnews.domain.repository.HomeRepository

open class DeleteFavoriteUseCase(
    private val homeRepository: HomeRepository,
) {
    open suspend operator fun invoke(market: MarketDomain) = homeRepository.deleteFavoriteMarket(market)
}