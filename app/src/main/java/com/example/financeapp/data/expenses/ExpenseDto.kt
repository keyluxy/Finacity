package com.example.financeapp.data.expenses

/**
 * DTO для расхода, используется только на data-слое.
 */
data class ExpenseDto(
    val id: Int,
    val title: String,
    val subtitle: String?,
    val amount: String,
    val emoji: String?
) 