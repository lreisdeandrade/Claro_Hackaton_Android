package br.com.claro.hackaton.android

import android.app.Application
import br.com.claro.hackaton.nfcservice.model.LoggingInterceptor
import br.com.claro.hackaton.nfcservice.model.NfcApiModule
import com.jakewharton.threetenabp.AndroidThreeTen
import timber.log.Timber

/**
 * Created by Leandro on 22/02/2018.
 */
class AppContext : Application() {

    companion object {
        lateinit var instance: AppContext private set

    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        initializeTimezone()
        initializeTimber()
        initializeApiModules()
    }

    private fun initializeTimezone() {
        AndroidThreeTen.init(this)
    }

    private fun initializeTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun initializeApiModules() {
        //Initialize ApiModule Singleton
        NfcApiModule.setRetrofit(LoggingInterceptor.LogLevel.FULL);
    }
}
