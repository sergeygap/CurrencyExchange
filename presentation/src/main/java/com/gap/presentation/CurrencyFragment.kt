package com.gap.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.gap.presentation.adapters.CurrencyAdapter
import com.gap.presentation.databinding.FragmentCurrencyBinding
import com.gap.presentation.viewModels.CurrencyViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch


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
        workWithSwipeToRefresh()
        viewModel.timeLD.observe(viewLifecycleOwner) {
            binding.tvLastUpdate.text = getString(R.string.currency_time, it)
        }
        viewModel.valuteListLD.observe(viewLifecycleOwner) {
            adapter.differ.submitList(it)
        }
        viewModel.progressLD.observe(viewLifecycleOwner) {
            if (it) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.INVISIBLE
            }
        }
        viewModel.errorLD.observe(viewLifecycleOwner) {
            if (it) {
                Toast.makeText(requireContext(), R.string.error, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun workWithSwipeToRefresh() {
        binding.swipeToRefreshLayout.setOnRefreshListener {
            viewModel.getCurrencyList()
            binding.swipeToRefreshLayout.isRefreshing = false
        }
    }

    private fun workWithViewModel() {
        viewModel.getCurrencyList()
        viewModel.startAutoRefresh()
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}