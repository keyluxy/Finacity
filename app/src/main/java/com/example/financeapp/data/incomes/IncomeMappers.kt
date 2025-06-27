package com.example.financeapp.data.incomes

import com.example.financeapp.domain.incomes.Income

/**
 * Маппер из DTO в domain-модель.
 */
fun IncomeDto.toDomain(): Income = Income(
    id = id.toString(),
    title = title,
    amount = amount,
    emoji = emoji
) 