package com.example.financeapp.data.expenses

import com.example.financeapp.data.remote.TransactionApi
import com.example.financeapp.data.expenses.ExpenseDto
import com.example.financeapp.data.remote.dto.TransactionResponse

/**
 * Источник данных расходов из сети.
 */
class ExpenseRemoteDataSource(private val api: TransactionApi) {
    suspend fun getExpenses(token: String, from: Long, to: Long): List<ExpenseDto> {
        val accounts = api.getAccounts("Bearer $token")
        val allExpenses = mutableListOf<ExpenseDto>()
        for (account in accounts) {
            val transactions = api.getTransactionsForAccount("Bearer $token", account.id.toString(), from, to)
            transactions.filter { it.category?.isIncome == false }.forEach { transaction ->
                allExpenses += transaction.toExpenseDto()
            }
        }
        return allExpenses
    }
}

fun TransactionResponse.toExpenseDto(): ExpenseDto = ExpenseDto(
    id = id,
    title = category?.name ?: "Без категории",
    subtitle = comment,
    amount = amount,
    emoji = category?.emoji
) 