package com.example.financeapp.data.account

import com.example.financeapp.domain.account.Account

/**
 * Маппер из DTO в domain-модель.
 */
fun AccountDto.toDomain(): Account = Account(
    id = id,
    name = name,
    balance = balance,
    currency = currency
) 