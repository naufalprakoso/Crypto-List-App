package com.naufalprakoso.cryptotest.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.naufalprakoso.cryptotest.App
import com.naufalprakoso.cryptotest.database.entities.CurrencyInfoEntity
import com.naufalprakoso.cryptotest.databinding.ActivityDemoBinding
import com.naufalprakoso.cryptotest.ui.dialog.SortDataDialog
import javax.inject.Inject

class DemoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDemoBinding
    private lateinit var viewModel: CurrencyViewModel

    @Inject
    lateinit var viewModelFactory: CurrencyViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDemoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupInject()
        setupViewModel()
        setupButtons()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(CurrencyViewModel::class.java)
    }

    private fun setupInject() {
        (application as App).applicationComponent.inject(this)
    }

    private fun setupButtons() {
        setupLoadDataButton()
        setupSortDataButton()
    }

    private fun setupLoadDataButton() {
        binding.btnLoad.setOnClickListener {
            openFragment()
        }
    }

    private fun openFragment() {
        binding.btnLoad.visibility = View.GONE
        supportFragmentManager.beginTransaction()
            .replace(binding.container.id, CurrencyListFragment.newInstance())
            .commit()
    }

    private fun setupSortDataButton() {
        val dialogSortData = SortDataDialog(this)
        binding.btnSort.setOnClickListener {
            dialogSortData.show()
        }

        dialogSortData.setDialogResult(object : SortDataDialog.SortDataResult {
            override fun onResult(
                type: CurrencyInfoEntity.SortType,
                order: CurrencyInfoEntity.SortOrder
            ) {
                viewModel.sortData(type, order)
            }
        })
    }
}