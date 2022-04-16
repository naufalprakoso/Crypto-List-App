package com.naufalprakoso.cryptotest

import android.app.Application
import com.naufalprakoso.cryptotest.dagger.AppModule
import com.naufalprakoso.cryptotest.dagger.ApplicationComponent
import com.naufalprakoso.cryptotest.dagger.DaggerApplicationComponent

class App : Application() {

    val applicationComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}