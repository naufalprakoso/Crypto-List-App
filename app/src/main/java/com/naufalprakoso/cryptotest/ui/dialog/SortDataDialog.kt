package com.naufalprakoso.cryptotest.ui.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import android.widget.RadioButton
import com.naufalprakoso.cryptotest.R
import com.naufalprakoso.cryptotest.database.entities.CurrencyInfoEntity
import com.naufalprakoso.cryptotest.databinding.DialogSortDataBinding

class SortDataDialog(context: Context) : Dialog(context, R.style.Theme_Dialog) {

    private lateinit var binding: DialogSortDataBinding
    private var dialogResult: SortDataResult? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogSortDataBinding.inflate(layoutInflater)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(binding.root)

        setupButton()
    }

    private fun setupButton() {
        binding.btnSort.setOnClickListener { saveSort() }
    }

    private fun saveSort() {
        val getType = findViewById<RadioButton>(binding.radioGroupAttr.checkedRadioButtonId).text.toString()
        val getOrder = findViewById<RadioButton>(binding.radioGroupBy.checkedRadioButtonId).text.toString()
        val type = CurrencyInfoEntity.SortType.values().find { it.name == getType.uppercase() }
        val order = CurrencyInfoEntity.SortOrder.values().find { it.name == getOrder.uppercase() }

        if (dialogResult != null && type != null && order != null) {
            dialogResult!!.onResult(type, order)
        }
        this.dismiss()
    }

    fun setDialogResult(result: SortDataResult) {
        dialogResult = result
    }

    interface SortDataResult {
        fun onResult(type: CurrencyInfoEntity.SortType, order: CurrencyInfoEntity.SortOrder)
    }
}