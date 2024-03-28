package com.gap.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.gap.presentation.adapters.CurrencyAdapter
import com.gap.presentation.databinding.FragmentCurrencyBinding
import com.gap.presentation.viewModels.CurrencyViewModel


class CurrencyFragment : Fragment() {

    private val viewModel by lazy {
        ViewModelProvider(this)[CurrencyViewModel::class.java]
    }
    private val binding: FragmentCurrencyBinding
        get() = _binding ?: throw RuntimeException("CurrencyFragment == null")
    private var _binding: FragmentCurrencyBinding? = null
    private lateinit var adapter: CurrencyAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCurrencyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        workWithViewModel()
        workWithUi()
    }

    private fun workWithUi() {
        adapter = CurrencyAdapter()
        binding.rvCurrency.adapter = adapter
        viewModel.timeLD.observe(viewLifecycleOwner) {
            binding.tvLastUpdate.text = it
        }
        viewModel.valuteListLD.observe(viewLifecycleOwner) {
            adapter.differ.submitList(it)
        }
    }

    private fun workWithViewModel() {
        viewModel.getCurrencyList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}