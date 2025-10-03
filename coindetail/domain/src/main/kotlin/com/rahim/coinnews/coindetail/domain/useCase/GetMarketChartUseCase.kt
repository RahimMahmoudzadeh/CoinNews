package com.rahim.coinnews.coindetail.domain.useCase

import com.rahim.coinnews.coindetail.domain.model.MarketChartDomain
import com.rahim.coinnews.coindetail.domain.repository.CoinDetailRepository
import com.rahim.coinnews.core.utils.Errors
import com.rahim.coinnews.core.utils.Resource
import kotlinx.coroutines.flow.Flow

class GetMarketChartUseCase(
    private val repository: CoinDetailRepository,
) {
    operator fun invoke(id: String): Flow<Resource<MarketChartDomain, Errors>> =
        repository.fetchChart(id = id)
}
