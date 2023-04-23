package com.example.peoplefind.app

import android.app.Application
import com.example.peoplefind.R
import com.example.peoplefind.di.appModule
import com.example.peoplefind.di.dataModule
import com.example.peoplefind.di.domainModule
import koleton.SkeletonLoader
import koleton.SkeletonLoaderFactory
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class App : Application(), SkeletonLoaderFactory {
    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(appModule, dataModule, domainModule)
        }
    }

    override fun newSkeletonLoader(): SkeletonLoader {
        return SkeletonLoader.Builder(this)
            .color(R.color.purple_500)
            .cornerRadius(32F)
            .build()
    }
}