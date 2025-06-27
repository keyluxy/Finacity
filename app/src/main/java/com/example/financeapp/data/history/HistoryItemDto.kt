package com.example.financeapp.data.history

/**
 * DTO для элемента истории.
 */
data class HistoryItemDto(
    val id: Int,
    val title: String,
    val subtitle: String?,
    val amount: String,
    val date: String,
    val icon: Int?
) 