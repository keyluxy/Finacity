package com.example.financeapp.navigation

sealed class Screen(val route: String, val title: String) {
    object Expenses : Screen("expenses", "Расходы")
    object Income : Screen("income", "Доходы")
    object Account : Screen("account", "Счет")
    object Categories : Screen("categories", "Статьи")
    object Settings : Screen("settings", "Настройки")
} 