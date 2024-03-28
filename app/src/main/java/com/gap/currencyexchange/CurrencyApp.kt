package com.gap.currencyexchange

import android.app.Application
import com.gap.data.CurrencyRepositoryImpl
import com.gap.domain.CurrencyRepository
import com.gap.presentation.viewModels.CurrencyViewModel
import com.gap.presentation.viewModels.ProvideViewModels

class CurrencyApp : Application(), ProvideViewModels {
    private lateinit var currencyRepository: CurrencyRepository
    private lateinit var currencyViewModel: CurrencyViewModel

    override fun onCreate() {
        super.onCreate()
        currencyRepository = CurrencyRepositoryImpl()
        currencyViewModel = CurrencyViewModel(currencyRepository)
    }

    override fun currencyViewModel(): CurrencyViewModel {
        return currencyViewModel
    }
}
