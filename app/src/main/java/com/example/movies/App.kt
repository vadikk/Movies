package com.example.movies

import android.app.Application
import com.example.common.dispatcherModule
import com.example.common.navigationModule
import com.example.detail.presentation.featureDetailModule
import com.example.favorite.featureFavoriteModule
import com.example.popular.featurePopularModule
import com.example.profile.presentation.featureProfileModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidContext(this@App)
            modules(
                navigationModule,
                dispatcherModule,
                featurePopularModule,
                featureDetailModule,
                featureFavoriteModule,
                featureProfileModule
            )
        }
    }
}