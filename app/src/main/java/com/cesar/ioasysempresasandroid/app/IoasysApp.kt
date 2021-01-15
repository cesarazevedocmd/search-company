package com.cesar.ioasysempresasandroid.app

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class IoasysApp: Application() {
    companion object {
        var uid: String? = null
        var client: String? = null
        var accessToken: String? = null
    }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@IoasysApp)
            modules(ioasysModules)
        }
    }
}