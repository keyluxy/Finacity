package com.example.financeapp.domain.account

/**
 * Use-case для получения счетов.
 */
class GetAccountsUseCase(private val repository: AccountRepository) {
    suspend operator fun invoke(token: String): List<Account> {
        return repository.getAccounts(token)
    }
} 