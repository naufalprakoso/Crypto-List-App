package com.naufalprakoso.cryptotest.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currency_info")
data class CurrencyInfoEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "symbol")
    val symbol: String
) {
    enum class SortType {
        ID, NAME, SYMBOL
    }

    enum class SortOrder {
        ASCENDING, DESCENDING
    }

}