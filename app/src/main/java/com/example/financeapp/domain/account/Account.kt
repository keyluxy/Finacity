package com.example.financeapp.domain.account

/**
 * Domain-модель счета.
 */
data class Account(
    val id: String,
    val name: String,
    val balance: String,
    val currency: String
) 