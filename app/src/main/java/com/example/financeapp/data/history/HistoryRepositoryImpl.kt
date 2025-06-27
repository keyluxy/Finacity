package com.example.financeapp.data.history

import com.example.financeapp.domain.history.HistoryItem
import com.example.financeapp.domain.history.HistoryRepository

/**
 * Реализация репозитория истории.
 */
class HistoryRepositoryImpl(
    private val remoteDataSource: HistoryRemoteDataSource
) : HistoryRepository {
    override suspend fun getHistory(token: String, from: Long, to: Long): List<HistoryItem> {
        return remoteDataSource.getHistory(token, from, to).map { it.toDomain() }
    }
} 