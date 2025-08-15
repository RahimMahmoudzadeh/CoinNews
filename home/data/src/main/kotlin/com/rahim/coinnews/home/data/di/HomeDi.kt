package com.rahim.coinnews.home.data.di

import com.rahim.coinnews.domain.repository.HomeRepository
import com.rahim.coinnews.domain.useCase.GetMarketsUseCase
import com.rahim.coinnews.home.data.api.HomeApi
import com.rahim.coinnews.home.data.api.HomeApiImpl
import com.rahim.coinnews.home.data.repository.HomeRepositoryImpl
import org.koin.dsl.module

val homeModule = module {
    single<HomeApi> { HomeApiImpl(get()) }
    single<HomeRepository> { HomeRepositoryImpl(get()) }
    single { GetMarketsUseCase(get()) }
}