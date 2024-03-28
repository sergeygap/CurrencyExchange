package com.gap.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gap.domain.entities.Valute
import com.gap.domain.useCases.GetCurrencyListUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class CurrencyViewModel : ViewModel() {
    private val repositoryImpl = CurrencyRepositoryImpl()
    private val getCurrencyList = GetCurrencyListUseCase(repositoryImpl)
    private val customCoroutineScope = CoroutineScope(Dispatchers.Main)
    private val _timeLD = MutableLiveData<String>()
    val timeLD: LiveData<String>
        get() = _timeLD

    private val _valuteListLD = MutableLiveData<List<Valute>>()
    val valuteListLD: LiveData<List<Valute>>
        get() = _valuteListLD

    private val _progressLD = MutableLiveData<Boolean>()
    val progressLD: LiveData<Boolean>
        get() = _progressLD

    private val _errorLD = MutableLiveData<Boolean>()
    val errorLD: LiveData<Boolean>
        get() = _errorLD

    fun getCurrencyList() {
        viewModelScope.launch {
            _progressLD.value = true
            val currency = getCurrencyList.invoke()
            _errorLD.value = currency.timestamp.isEmpty()
            _timeLD.value = currency.timestamp
            _valuteListLD.value = currency.valute
            _progressLD.value = false
        }
    }

    fun startAutoRefresh() {
        customCoroutineScope.launch {
            while (isActive) {
                delay(30000)
                getCurrencyList()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        customCoroutineScope.cancel()
    }
}
