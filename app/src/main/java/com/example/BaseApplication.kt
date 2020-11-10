package com.example

import android.app.Application
import com.example.data.BuildConfig
import com.example.data.local.db.databaseModule
import com.example.di.splashscreenModule
import com.example.genre.di.genreModule
import com.example.movielist.di.movieModule
import com.example.network.di.networkModule
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
        val appModules = networkModule + databaseModule + genreModule + movieModule + splashscreenModule
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@BaseApplication)
            modules(appModules)
        }
    }
}