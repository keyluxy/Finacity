package com.example.financeapp.data.account

import com.example.financeapp.data.remote.TransactionApi

/**
 * Источник данных счетов из сети.
 */
class AccountRemoteDataSource(private val api: TransactionApi) {
    suspend fun getAccounts(token: String): List<AccountDto> {
        return api.getAccounts("Bearer $token").map {
            AccountDto(
                id = it.id.toString(),
                name = it.name,
                balance = it.balance,
                currency = it.currency
            )
        }
    }
} 