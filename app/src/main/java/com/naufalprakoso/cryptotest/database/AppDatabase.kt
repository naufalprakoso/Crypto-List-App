package com.naufalprakoso.cryptotest.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.naufalprakoso.cryptotest.database.dao.CurrencyInfoDao
import com.naufalprakoso.cryptotest.database.entities.CurrencyInfoEntity

@Database(entities = [CurrencyInfoEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getCurrencyInfoDao(): CurrencyInfoDao
}