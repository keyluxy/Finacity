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
        Log.d("IncomeRepository", "Начинаем загрузку доходов")
        try {
            val accounts = api.getAccounts("Bearer $token")
            Log.d("IncomeRepository", "Получено счетов: ${accounts.size}. IDs: ${accounts.map { it.id }}")
            val allIncomes = mutableListOf<Income>()
            for (account in accounts) {
                Log.d("IncomeRepository", "Запрос транзакций для счета ID: ${account.id}")
                val transactions = api.getTransactionsForAccount("Bearer $token", account.id.toString(), from, to)
                Log.d("IncomeRepository", "Счет ID: ${account.id}, получено транзакций: ${transactions.size}")

                transactions.forEach { transaction ->
                    Log.d("IncomeRepository", "Транзакция: $transaction, isIncome: ${transaction.category?.isIncome}")
                }

                val incomesForAccount = transactions
                    .filter { it.category?.isIncome == true }
                    .map { it.toIncome() }

                Log.d("IncomeRepository", "Счет ID: ${account.id}, найдено доходов: ${incomesForAccount.size}")
                allIncomes += incomesForAccount
            }
            Log.d("IncomeRepository", "Всего доходов после фильтрации: ${allIncomes.size}")
            return allIncomes
        } catch (e: Exception) {
            Log.e("IncomeRepository", "Ошибка при загрузке доходов", e)
            throw e
        }
    }
} 