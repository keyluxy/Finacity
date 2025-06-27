package com.example.financeapp.domain.expenses

/**
 * Use-case для получения расходов.
 */
class GetExpensesUseCase(private val repository: ExpenseRepository) {
    suspend operator fun invoke(token: String, from: Long, to: Long): List<Expense> {
        return repository.getExpenses(token, from, to)
    }
} 