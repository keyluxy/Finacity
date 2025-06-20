package com.example.financeapp.data.repository

import android.util.Log
import com.example.financeapp.data.model.Income
import com.example.financeapp.data.remote.TransactionApi
import com.example.financeapp.data.remote.dto.toIncome
import javax.inject.Inject

class IncomeRepository @Inject constructor(
    private val api: TransactionApi
) {
    suspend fun getIncomes(token: String, from: Long, to: Long): List<Income> {
        val accounts = api.getAccounts("Bearer $token")
        Log.d("IncomeRepository", "Все id аккаунтов: ${accounts.map { it.id }}")
        val allIncomes = mutableListOf<Income>()
        for (account in accounts) {
            val transactions = api.getTransactionsForAccount("Bearer $token", account.id.toString(), from, to)
            Log.d("IncomeRepository", "Account: ${account.id}, транзакций: ${transactions.size}")
            transactions.forEach { Log.d("IncomeRepository", it.toString()) }
            allIncomes += transactions
                .filter { it.category?.isIncome == true }
                .map { it.toIncome() }
        }
        Log.d("IncomeRepository", "Всего доходов после фильтрации: ${allIncomes.size}")
        return allIncomes
    }
} 