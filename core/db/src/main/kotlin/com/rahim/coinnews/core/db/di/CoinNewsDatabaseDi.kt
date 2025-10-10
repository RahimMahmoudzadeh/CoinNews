package com.rahim.coinnews.core.db.di

import androidx.room.Room
import com.rahim.coinnews.core.db.AppDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

const val DATABASE_NAME = "Coin News Database"
val coinNewsDatabaseModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            AppDatabase::class.java,
            DATABASE_NAME
        ).build()
    }
    single { get<AppDatabase>().favoriteDao() }
}