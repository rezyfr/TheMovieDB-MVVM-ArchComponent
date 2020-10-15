package com.example.axiatatest

import android.app.Application
import com.example.axiatatest.di.networkModule
import com.example.axiatatest.di.repositoryModule
import com.example.axiatatest.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        startInject()
    }

    private fun startInject() {
        val appModules = networkModule + repositoryModule + viewModelModule
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@BaseApplication)
            modules(appModules)
        }
    }
}