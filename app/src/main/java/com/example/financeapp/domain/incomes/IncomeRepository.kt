package com.example.financeapp.domain.incomes

/**
 * Интерфейс репозитория доходов.
 */
interface IncomeRepository {
    suspend fun getIncomes(token: String, from: Long, to: Long): List<Income>
} 