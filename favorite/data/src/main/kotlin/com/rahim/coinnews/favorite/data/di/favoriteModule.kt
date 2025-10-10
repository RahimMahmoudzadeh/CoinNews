package com.rahim.coinnews.favorite.data.di

import com.rahim.coinnews.favorite.data.repo.FavoriteRepositoryImpl
import com.rahim.coinnews.favorite.domain.repo.FavoriteRepository
import org.koin.dsl.module

val favoriteModule = module {
    single<FavoriteRepository> { FavoriteRepositoryImpl(get()) }
}