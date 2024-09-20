package com.santorence.secveniafilmapp.core

import android.app.Application
import com.santorence.secveniafilmapp.core.di.appModule
import com.santorence.secveniafilmapp.fimsScreen.di.filmsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(appModule, filmsModule)
        }
    }
}