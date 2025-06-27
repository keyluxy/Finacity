package com.example.financeapp.domain.incomes

/**
 * Use-case для получения доходов.
 */
class GetIncomesUseCase(private val repository: IncomeRepository) {
    suspend operator fun invoke(token: String, from: Long, to: Long): List<Income> {
        return repository.getIncomes(token, from, to)
    }
} 