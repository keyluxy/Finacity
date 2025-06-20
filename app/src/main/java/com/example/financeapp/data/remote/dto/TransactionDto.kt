package com.example.financeapp.data.remote.dto

import com.example.financeapp.data.model.Expense

data class AccountBriefDto(
    val id: String,
    val name: String,
    val balance: String,
    val currency: String
)

data class TransactionResponse(
    val id: String,
    val account: AccountBriefDto,
    val category: CategoryDto?,
    val amount: String,
    val transactionDate: Long,
    val comment: String?,
    val createdAt: String,
    val updatedAt: String
)

data class CategoryDto(
    val id: String,
    val name: String,
    val icon: String,
    val type: String // "income" или "outcome"
)

fun TransactionResponse.toExpense(): Expense = Expense(
    id = id,
    title = category?.name ?: "Без категории",
    subtitle = comment,
    amount = amount,
    icon = null // Можно добавить маппинг emoji->drawable
) 