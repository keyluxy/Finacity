package com.example.financeapp.data.expenses

import com.example.financeapp.domain.expenses.Expense

/**
 * Маппер из DTO в domain-модель.
 */
fun ExpenseDto.toDomain(): Expense = Expense(
    id = id.toString(),
    title = title,
    subtitle = subtitle,
    amount = amount,
    emoji = emoji
) 