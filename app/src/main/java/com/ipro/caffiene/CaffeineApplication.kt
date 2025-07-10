package com.ipro.caffiene

import android.app.Application
import com.ipro.caffiene.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class CaffeineApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CaffeineApplication)
            modules(appModule)
        }
    }
}