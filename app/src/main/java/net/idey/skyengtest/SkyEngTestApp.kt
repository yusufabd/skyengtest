package net.idey.skyengtest

import android.app.Application
import net.idey.skyengtest.di.networkingModule
import net.idey.skyengtest.di.screenModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class SkyEngTestApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@SkyEngTestApp)
            modules(listOf(networkingModule, screenModule))
        }
    }

}