package com.naufalprakoso.cryptotest.dagger

import com.naufalprakoso.cryptotest.currency_info.CurrencyInfoModule
import com.naufalprakoso.cryptotest.ui.DemoActivity
import com.naufalprakoso.cryptotest.database.AppDatabase
import com.naufalprakoso.cryptotest.database.DatabaseModule
import com.naufalprakoso.cryptotest.network.NetworkModule
import com.naufalprakoso.cryptotest.ui.CurrencyListFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        DatabaseModule::class,
        NetworkModule::class,
        ViewModelModule::class,

        // Feature
        CurrencyInfoModule::class
    ]
)
interface ApplicationComponent {
    fun appDatabase(): AppDatabase

    fun inject(activity: DemoActivity)

    fun inject(fragment: CurrencyListFragment)
}
