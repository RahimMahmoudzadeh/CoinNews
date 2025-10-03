package com.rahim.coinnews.coindetail.data.di

import com.rahim.coinnews.coindetail.data.repository.CoinDetailRepositoryImpl
import com.rahim.coinnews.coindetail.domain.repository.CoinDetailRepository
import com.rahim.coinnews.coindetail.domain.useCase.GetMarketChartUseCase
import com.rahim.coinnews.coindetail.domain.useCase.GetMarketDetailUseCase
import com.rahim.coinnews.coindetail.domain.useCase.ToggleFavoriteMarketListUseCase
import org.koin.dsl.module

val coinDetailDi= module {
    single<CoinDetailRepository> { CoinDetailRepositoryImpl() }
    single { GetMarketChartUseCase(get()) }
    single { GetMarketDetailUseCase(get()) }
    single { ToggleFavoriteMarketListUseCase(get()) }
}