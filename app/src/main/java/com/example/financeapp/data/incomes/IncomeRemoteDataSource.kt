package com.example.financeapp.data.incomes

import com.example.financeapp.data.remote.TransactionApi
import com.example.financeapp.data.remote.dto.TransactionResponse

/**
 * Источник данных доходов из сети.
 */
class IncomeRemoteDataSource(private val api: TransactionApi) {
    suspend fun getIncomes(token: String, from: Long, to: Long): List<IncomeDto> {
        val accounts = api.getAccounts("Bearer $token")
        val allIncomes = mutableListOf<IncomeDto>()
        for (account in accounts) {
            val transactions = api.getTransactionsForAccount("Bearer $token", account.id.toString(), from, to)
            transactions.filter { it.category?.isIncome == true }.forEach { transaction ->
                allIncomes += transaction.toIncomeDto()
            }
        }
        return allIncomes
    }
}

fun TransactionResponse.toIncomeDto(): IncomeDto = IncomeDto(
    id = id,
    title = category?.name ?: "Без категории",
    amount = amount,
    emoji = category?.emoji
) 