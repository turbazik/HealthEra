package com.turbazik.healthera

import android.app.Application
import com.facebook.stetho.Stetho
import com.turbazik.healthera.di.dataModule
import com.turbazik.healthera.di.domainModule
import com.turbazik.healthera.di.networkModule
import com.turbazik.healthera.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initLoggers()
        initKoin()
    }

    private fun initLoggers() {
        if (!BuildConfig.DEBUG) return

        Stetho.initializeWithDefaults(this)
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@App)
            modules(
                networkModule,
                dataModule,
                domainModule,
                viewModelModule,
            )
        }
    }
}
