package com.example.financeapp.data.history

import com.example.financeapp.data.remote.TransactionApi
import com.example.financeapp.data.remote.dto.TransactionResponse

/**
 * Источник данных истории из сети.
 */
class HistoryRemoteDataSource(private val api: TransactionApi) {
    suspend fun getHistory(token: String, from: Long, to: Long): List<HistoryItemDto> {
        val accounts = api.getAccounts("Bearer $token")
        val allHistory = mutableListOf<HistoryItemDto>()
        for (account in accounts) {
            val transactions = api.getTransactionsForAccount("Bearer $token", account.id.toString(), from, to)
            transactions.forEach { transaction ->
                allHistory += transaction.toHistoryItemDto()
            }
        }
        return allHistory
    }
}

fun TransactionResponse.toHistoryItemDto(): HistoryItemDto = HistoryItemDto(
    id = id,
    title = category?.name ?: "Без категории",
    subtitle = comment,
    amount = amount,
    date = transactionDate,
    icon = null // Можно добавить маппинг по category?.emoji или id
) 