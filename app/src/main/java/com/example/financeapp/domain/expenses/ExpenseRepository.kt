package com.example.financeapp.domain.expenses

/**
 * Интерфейс репозитория расходов.
 * Контракт для бизнес-логики.
 */
interface ExpenseRepository {
    suspend fun getExpenses(token: String, from: Long, to: Long): List<Expense>
} 