package com.example.financeapp.domain.history

/**
 * Use-case для получения истории.
 */
class GetHistoryUseCase(private val repository: HistoryRepository) {
    suspend operator fun invoke(token: String, from: Long, to: Long): List<HistoryItem> {
        return repository.getHistory(token, from, to)
    }
} 