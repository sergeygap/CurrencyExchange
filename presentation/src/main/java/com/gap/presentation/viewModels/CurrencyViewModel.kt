package com.gap.presentation.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gap.domain.entities.Valute
import com.gap.domain.useCases.GetCurrencyListUseCase
import kotlinx.coroutines.launch

class CurrencyViewModel : ViewModel() {

    private val repositoryImpl = CurrencyRepositoryImpl()
    private val getCurrencyList = GetCurrencyListUseCase(repositoryImpl)

    private val _timeLD = MutableLiveData<String>()
    val timeLD: LiveData<String>
        get() = _timeLD

    private val _valuteListLD = MutableLiveData<List<Valute>>()
    val valuteListLD: LiveData<List<Valute>>
        get() = _valuteListLD

    fun getCurrencyList() {
        viewModelScope.launch {
            val currency = getCurrencyList.invoke()
            _timeLD.value = currency.timestamp
            _valuteListLD.value = currency.valute
        }
    }

    companion object {
        private const val TAG = "CurrencyViewModel"
    }

}