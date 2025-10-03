package com.rahim.coinnews.coindetail.domain.useCase

import com.rahim.coinnews.coindetail.domain.model.MarketDomainLayerCoinDetail
import com.rahim.coinnews.coindetail.domain.repository.CoinDetailRepository

open class ToggleFavoriteMarketListUseCase (
    private val repository: CoinDetailRepository,
) {
    open suspend operator fun invoke(market: MarketDomainLayerCoinDetail) = repository.toggleFavoriteMarket(market)
}
