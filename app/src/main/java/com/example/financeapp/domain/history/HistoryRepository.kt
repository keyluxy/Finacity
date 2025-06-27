package com.example.financeapp.domain.history

/**
 * Интерфейс репозитория истории.
 */
interface HistoryRepository {
    suspend fun getHistory(token: String, from: Long, to: Long): List<HistoryItem>
} 