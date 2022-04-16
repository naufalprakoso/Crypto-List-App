package com.naufalprakoso.cryptotest.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.naufalprakoso.cryptotest.database.entities.CurrencyInfoEntity
import com.naufalprakoso.cryptotest.databinding.ItemCurrencyBinding

class CurrencyAdapter(context: Context) : RecyclerView.Adapter<CurrencyAdapter.ViewHolder>() {

    private val inflater = LayoutInflater.from(context)
    private val currencies = arrayListOf<CurrencyInfoEntity>()
    private lateinit var binding: ItemCurrencyBinding

    fun setCurrencies(currencies: List<CurrencyInfoEntity>) {
        this.currencies.clear()
        this.currencies.addAll(currencies)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyAdapter.ViewHolder {
        binding = ItemCurrencyBinding.inflate(inflater, parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: CurrencyAdapter.ViewHolder, position: Int) {
        holder.bindItem(currencies[position])
    }

    override fun getItemCount(): Int = currencies.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindItem(currency: CurrencyInfoEntity) {
            binding.tvId.text = currency.id.first().uppercase()
            binding.tvName.text = currency.name
            binding.tvSymbol.text = currency.symbol
        }
    }
}