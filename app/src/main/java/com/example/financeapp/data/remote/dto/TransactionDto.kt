package com.example.financeapp.data.remote.dto

import com.example.financeapp.data.model.Expense
import com.example.financeapp.data.model.Income

data class AccountBriefDto(
    val id: Int,
    val name: String,
    val balance: String,
    val currency: String
)

data class CategoryDto(
    val id: Int,
    val name: String,
    val emoji: String,
    val isIncome: Boolean
)

data class TransactionResponse(
    val id: Int,
    val account: AccountBriefDto,
    val category: CategoryDto?,
    val amount: String,
    val transactionDate: String,
    val comment: String?,
    val createdAt: String,
    val updatedAt: String
)

fun TransactionResponse.toExpense(): Expense = Expense(
    id = id.toString(),
    title = category?.name ?: "Без категории",
    subtitle = comment,
    amount = amount,
    emoji = category?.emoji,
    icon = null // Можно добавить маппинг emoji->drawable
)

fun TransactionResponse.toIncome(): Income = Income(
    id = id.toString(),
    title = category?.name ?: "Без категории",
    amount = amount,
    icon = null // Можно добавить маппинг emoji->drawable
) 