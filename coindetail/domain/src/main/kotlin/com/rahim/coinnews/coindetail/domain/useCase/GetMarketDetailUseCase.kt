package com.rahim.coinnews.coindetail.domain.useCase

import com.rahim.coinnews.coindetail.domain.model.MarketChartDomainLayerCoinDetail
import com.rahim.coinnews.coindetail.domain.model.MarketDetailDomainLayerCoinDetail
import com.rahim.coinnews.coindetail.domain.repository.CoinDetailRepository
import com.rahim.coinnews.core.utils.Errors
import com.rahim.coinnews.core.utils.Resource
import kotlinx.coroutines.flow.Flow

class GetMarketDetailUseCase(
    private val repository: CoinDetailRepository,
) {
    operator fun invoke(id: String): Flow<Resource<MarketDetailDomainLayerCoinDetail, Errors>> =
        repository.fetchDetail(id = id)
}
