package com.rahim.coinnews.coindetail.domain.useCase

import com.rahim.coinnews.coindetail.domain.model.MarketDomain
import com.rahim.coinnews.coindetail.domain.repository.CoinDetailRepository

class ToggleFavoriteMarketListUseCase (
    private val repository: CoinDetailRepository,
) {
//    suspend operator fun invoke(market: MarketDomain) = repository.toggleFavoriteMarket(market)
}
