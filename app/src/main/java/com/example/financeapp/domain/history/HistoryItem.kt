package com.example.financeapp.domain.history

/**
 * Domain-модель элемента истории.
 */
data class HistoryItem(
    val id: String,
    val title: String,
    val subtitle: String?,
    val amount: String,
    val date: String,
    val icon: Int? = null
) 