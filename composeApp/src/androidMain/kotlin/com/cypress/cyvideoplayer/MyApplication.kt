package com.cypress.cyvideoplayer

import android.app.Application
import com.cypress.cyvideoplayer.di.initKoin
import org.koin.android.ext.koin.androidContext

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@MyApplication)
        }
    }
}