package com.naufalprakoso.cryptotest.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.naufalprakoso.cryptotest.App
import com.naufalprakoso.cryptotest.databinding.FragmentCurrencyListBinding

class CurrencyListFragment : Fragment() {

    private lateinit var binding: FragmentCurrencyListBinding
    private lateinit var adapter: CurrencyAdapter
    private val viewModel: CurrencyViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context?.let { context ->
            setupInject(context)
        }
    }

    private fun setupInject(context: Context) {
        (context.applicationContext as App).applicationComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCurrencyListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupAdapter()
        setupRecyclerView()
        setupData()
    }

    private fun setupAdapter() {
        adapter = CurrencyAdapter(requireContext())
    }

    private fun setupRecyclerView() {
        binding.rvCurrency.layoutManager = LinearLayoutManager(requireContext())
        binding.rvCurrency.adapter = adapter
    }

    private fun setupData() {
        viewModel.loadData()
        viewModel.currenciesData.observe(viewLifecycleOwner) { data ->
            binding.rvCurrency.removeAllViews()
            adapter.setCurrencies(data)
            adapter.notifyDataSetChanged()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = CurrencyListFragment()
    }
}