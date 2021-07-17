package com.example.imagefilter.utilities

import android.app.Application
import com.example.imagefilter.depedencyinjection.repositoryModul
import com.example.imagefilter.depedencyinjection.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

@Suppress("unused")
class AppConfig : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AppConfig)
            modules(listOf(repositoryModul, viewModelModule))
        }
    }
}