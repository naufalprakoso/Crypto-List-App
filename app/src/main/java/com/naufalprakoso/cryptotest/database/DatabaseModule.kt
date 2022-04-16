package com.naufalprakoso.cryptotest.database

import android.content.Context
import androidx.room.Room
import com.naufalprakoso.cryptotest.database.dao.CurrencyInfoDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun providesAppDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "crypto_db").build()
    }

    @Provides
    fun providesCurrencyInfoDao(appDatabase: AppDatabase): CurrencyInfoDao {
        return appDatabase.getCurrencyInfoDao()
    }
}