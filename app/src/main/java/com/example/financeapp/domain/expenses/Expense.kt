package com.example.financeapp.domain.expenses

/**
 * Domain-модель расхода.
 * Используется только в бизнес-логике и UI.
 */
data class Expense(
    val id: String,
    val title: String,
    val subtitle: String?,
    val amount: String,
    val emoji: String? = null
) 