package com.example.financeapp.data.remote.dto

import com.example.financeapp.data.model.Expense

// Примерная структура, уточни поля по Swagger
// Например: id, title, subtitle, amount

data class ExpenseDto(
    val id: String,
    val title: String,
    val subtitle: String?,
    val amount: String
) {
    fun toExpense(): Expense = Expense(
        id = id,
        title = title,
        subtitle = subtitle,
        amount = amount,
        icon = null // Можно добавить маппинг иконки по категории, если потребуется
    )
} 