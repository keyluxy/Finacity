package com.example.financeapp.data.repository

import com.example.financeapp.data.model.Expense
import com.example.financeapp.data.remote.TransactionApi
import com.example.financeapp.data.remote.dto.toExpense
import javax.inject.Inject

class ExpenseRepository @Inject constructor(
    private val api: TransactionApi
) {
    suspend fun getExpenses(token: String, from: Long, to: Long): List<Expense> {
        val accounts = api.getAccounts("Bearer $token")
        val allExpenses = mutableListOf<Expense>()
        for (account in accounts) {
            val transactions = api.getTransactionsForAccount("Bearer $token", account.id, from, to)
            allExpenses += transactions
                .filter { it.category?.type == "outcome" }
                .map { it.toExpense() }
        }
        return allExpenses
    }
} 