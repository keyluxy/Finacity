package com.example.financeapp.data.repository

import android.util.Log
import com.example.financeapp.data.model.Expense
import com.example.financeapp.data.remote.TransactionApi
import com.example.financeapp.data.remote.dto.toExpense
import javax.inject.Inject

class ExpenseRepository @Inject constructor(
    private val api: TransactionApi
) {
    suspend fun getExpenses(token: String, from: Long, to: Long): List<Expense> {
        val accounts = api.getAccounts("Bearer $token")
        Log.d("ExpenseRepository", "Все id аккаунтов: ${accounts.map { it.id }}")
        val allExpenses = mutableListOf<Expense>()
        for (account in accounts) {
            val transactions = api.getTransactionsForAccount("Bearer $token", account.id.toString(), from, to)
            Log.d("ExpenseRepository", "Account: ${account.id}, транзакций: ${transactions.size}")
            transactions.forEach { Log.d("ExpenseRepository", it.toString()) }
            allExpenses += transactions
                .filter { it.category?.isIncome == false }
                .map { it.toExpense() }
        }
        Log.d("ExpenseRepository", "Всего расходов после фильтрации: ${allExpenses.size}")
        return allExpenses
    }
}


