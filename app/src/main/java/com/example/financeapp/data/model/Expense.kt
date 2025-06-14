package com.example.financeapp.data.model

import androidx.compose.ui.graphics.vector.ImageVector

data class Expense(
    val id: String,
    val title: String,
    val subtitle: String?, // например, "Аренда квартиры" - может быть null для некоторых трат
    val amount: String, // например, "100 000 ₽"
    val icon: Int? = null // Иконка для ведущего изображения
)

data class ExpenseDetailed(
    val id: String,
    val account: String, // например, "Сбербанк"
    val category: String, // например, "Ремонт"
    val amount: String, // например, "25 270 ₽"
    val date: String, // например, "25.02.2025"
    val time: String, // например, "23:41"
    val description: String?, // например, "Ремонт - фурнитура для дверей" - может быть null
    val createdAt: String // точное время создания записи
) 