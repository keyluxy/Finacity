package com.example.financeapp.data.account

/**
 * DTO для счета.
 */
data class AccountDto(
    val id: String,
    val name: String,
    val balance: String,
    val currency: String
) 