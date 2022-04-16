package com.naufalprakoso.cryptotest.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.naufalprakoso.cryptotest.database.entities.CurrencyInfoEntity

@Dao
interface CurrencyInfoDao {
    @Query("SELECT * FROM currency_info")
    fun getAllData(): List<CurrencyInfoEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(currencies: List<CurrencyInfoEntity>)
}