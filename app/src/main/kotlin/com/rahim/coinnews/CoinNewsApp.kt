package com.rahim.coinnews

import android.app.Application
import com.rahim.coinnews.coindetail.data.di.coinDetailDi
import com.rahim.coinnews.home.data.di.homeModule
import com.rahim.coinnews.network.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CoinNewsApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CoinNewsApp)
            modules(networkModule, homeModule, coinDetailDi)
        }
    }
}