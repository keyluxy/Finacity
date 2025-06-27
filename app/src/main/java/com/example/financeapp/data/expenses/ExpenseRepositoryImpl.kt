package com.example.financeapp.data.expenses

import com.example.financeapp.domain.expenses.Expense
import com.example.financeapp.domain.expenses.ExpenseRepository

/**
 * Реализация репозитория расходов.
 */
class ExpenseRepositoryImpl(
    private val remoteDataSource: ExpenseRemoteDataSource
) : ExpenseRepository {
    override suspend fun getExpenses(token: String, from: Long, to: Long): List<Expense> {
        return remoteDataSource.getExpenses(token, from, to).map { it.toDomain() }
    }
} 