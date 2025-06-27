package com.example.financeapp.domain.incomes

/**
 * Domain-модель дохода.
 */
data class Income(
    val id: String,
    val title: String,
    val amount: String,
    val emoji: String? = null
) 