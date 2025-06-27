package com.example.financeapp.data.account

import com.example.financeapp.domain.account.Account
import com.example.financeapp.domain.account.AccountRepository

/**
 * Реализация репозитория счетов.
 */
class AccountRepositoryImpl(
    private val remoteDataSource: AccountRemoteDataSource
) : AccountRepository {
    override suspend fun getAccounts(token: String): List<Account> {
        return remoteDataSource.getAccounts(token).map { it.toDomain() }
    }
} 