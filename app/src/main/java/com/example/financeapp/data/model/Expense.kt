package com.example.financeapp.data.model

import androidx.compose.ui.graphics.vector.ImageVector

data class Expense(
    val id: String,
    val title: String,
    val subtitle: String?,
    val amount: String,
    val emoji: String? = null,
    val icon: Int? = null
)

data class ExpenseDetailed(
    val id: String,
    val account: String,
    val category: String,
    val amount: String,
    val date: String,
    val time: String,
    val description: String?,
    val createdAt: String
) 