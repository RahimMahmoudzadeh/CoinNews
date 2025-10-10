package com.rahim.coinnews

import android.app.Application
import com.rahim.coinnews.coindetail.data.di.coinDetailModule
import com.rahim.coinnews.core.db.di.coinNewsDatabaseModule
import com.rahim.coinnews.favorite.data.di.favoriteModule
import com.rahim.coinnews.home.data.di.homeModule
import com.rahim.coinnews.network.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CoinNewsApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CoinNewsApp)
            modules(networkModule, homeModule, coinDetailModule,favoriteModule,coinNewsDatabaseModule)
        }
    }
}