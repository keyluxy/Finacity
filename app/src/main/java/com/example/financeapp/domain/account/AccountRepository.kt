package com.example.financeapp.domain.account

/**
 * Интерфейс репозитория счетов.
 */
interface AccountRepository {
    suspend fun getAccounts(token: String): List<Account>
} 