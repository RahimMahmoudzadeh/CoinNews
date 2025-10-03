package com.rahim.coinnews.coindetail.data.di

import com.rahim.coinnews.coindetail.data.api.CoinDetailApi
import com.rahim.coinnews.coindetail.data.api.CoinDetailApiImpl
import com.rahim.coinnews.coindetail.data.repository.CoinDetailRepositoryImpl
import com.rahim.coinnews.coindetail.domain.repository.CoinDetailRepository
import com.rahim.coinnews.coindetail.domain.useCase.GetMarketChartUseCase
import com.rahim.coinnews.coindetail.domain.useCase.GetMarketDetailUseCase
import com.rahim.coinnews.coindetail.domain.useCase.ToggleFavoriteMarketListUseCase
import org.koin.dsl.module

val coinDetailDi= module {
    single<CoinDetailRepository> { CoinDetailRepositoryImpl(get()) }
    single<CoinDetailApi> { CoinDetailApiImpl(get()) }
    single { GetMarketChartUseCase(get()) }
    single { GetMarketDetailUseCase(get()) }
    single { ToggleFavoriteMarketListUseCase(get()) }
}