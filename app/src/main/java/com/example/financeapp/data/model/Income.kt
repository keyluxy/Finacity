package com.example.financeapp.data.model

import androidx.compose.ui.graphics.vector.ImageVector

data class Income(
    val id: String,
    val title: String,
    val amount: String, // например, "500 000 ₽"
    val icon: Int? = null // Иконка для ведущего изображения
)

data class IncomeDetailed(
    val id: String,
    val account: String,
    val category: String,
    val amount: String,
    val date: String,
    val time: String,
    val description: String?,
    val createdAt: String
) 