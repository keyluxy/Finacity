package com.example.financeapp.data.history

import com.example.financeapp.domain.history.HistoryItem

/**
 * Маппер из DTO в domain-модель.
 */
fun HistoryItemDto.toDomain(): HistoryItem = HistoryItem(
    id = id.toString(),
    title = title,
    subtitle = subtitle,
    amount = amount,
    date = date,
    icon = icon
) 