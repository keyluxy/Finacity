package com.example.financeapp.data.incomes

import com.example.financeapp.domain.incomes.Income
import com.example.financeapp.domain.incomes.IncomeRepository

/**
 * Реализация репозитория доходов.
 */
class IncomeRepositoryImpl(
    private val remoteDataSource: IncomeRemoteDataSource
) : IncomeRepository {
    override suspend fun getIncomes(token: String, from: Long, to: Long): List<Income> {
        return remoteDataSource.getIncomes(token, from, to).map { it.toDomain() }
    }
} 