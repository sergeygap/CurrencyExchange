package com.gap.domain.useCases

import com.gap.domain.CurrencyRepository
import com.gap.domain.entities.Currency

class GetCurrencyListUseCase(
    private val repository: CurrencyRepository
) {
    suspend operator fun invoke(): Currency {
        return repository.getCurrencyList()
    }
}