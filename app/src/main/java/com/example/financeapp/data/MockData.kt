package com.example.financeapp.data

import com.example.financeapp.data.model.Account
import com.example.financeapp.data.model.Category
import com.example.financeapp.data.model.Expense
import com.example.financeapp.data.model.ExpenseDetailed
import com.example.financeapp.data.model.Income
import com.example.financeapp.data.model.IncomeDetailed
import com.example.financeapp.data.model.Transaction
import com.example.financeapp.R

object MockData {
    val expenses = listOf(
        Expense("1", "Аренда квартиры", null, "100 000 ₽", R.drawable.ic_home),
        Expense("2", "Одежда", null, "100 000 ₽", R.drawable.ic_person),
        Expense("3", "На собачку", "Джек", "100 000 ₽", R.drawable.ic_dog),
        Expense("4", "На собачку", "Энни", "100 000 ₽", R.drawable.ic_dog),
        Expense("5", "Ремонт квартиры", null, "100 000 ₽", R.drawable.ic_leading_element),
        Expense("6", "Продукты", null, "100 000 ₽", R.drawable.ic_eat),
        Expense("7", "Спортзал", null, "100 000 ₽", R.drawable.ic_sport),
        Expense("8", "Медицина", null, "100 000 ₽", R.drawable.ic_tabl)
    )

    val expensesDetailed = listOf(
        ExpenseDetailed(
            id = "1",
            account = "Сбербанк",
            category = "Ремонт",
            amount = "25 270 ₽",
            date = "25.02.2025",
            time = "23:41",
            description = "Ремонт - фурнитура для дверей",
            createdAt = "2025-02-25T23:41:00Z"
        ),
        ExpenseDetailed(
            id = "2",
            account = "Тинькофф",
            category = "Одежда",
            amount = "15 000 ₽",
            date = "24.02.2025",
            time = "18:30",
            description = "Куртка весенняя",
            createdAt = "2025-02-24T18:30:00Z"
        )
    )

    val income = listOf(
        Income("1", "Зарплата", "500 000 ₽", R.drawable.ic_income),
        Income("2", "Подработка", "100 000 ₽", R.drawable.ic_tabl)
    )

    val incomeDetailed = listOf(
        IncomeDetailed(
            id = "1",
            account = "Сбербанк",
            category = "Зарплата",
            amount = "500 000 ₽",
            date = "01.03.2025",
            time = "10:00",
            description = "Ежемесячная зарплата",
            createdAt = "2025-03-01T10:00:00Z"
        ),
        IncomeDetailed(
            id = "2",
            account = "Тинькофф",
            category = "Подработка",
            amount = "100 000 ₽",
            date = "05.03.2025",
            time = "15:00",
            description = "Проект за февраль",
            createdAt = "2025-03-05T15:00:00Z"
        )
    )

    val transactions = listOf(
        Transaction("t1", "Ремонт квартиры", "Ремонт - фурнитура для дверей", "20 000 ₽", "20%", "25.02.2025", "23:41", R.drawable.ic_leading_element),
        Transaction("t2", "На собачку", null, "80 000 ₽", "80%", "25.02.2025", "22:01", R.drawable.ic_dog)
    )

    val accounts = listOf(
        Account("1", "Сбербанк", "-670 000 ₽", "Российский рубль ₽", R.drawable.ic_bill),
        Account("2", "Тинькофф", "120 000 ₽", "Российский рубль ₽", R.drawable.ic_bill)
    )

    val categories = listOf(
        Category("1", "Аренда квартиры", R.drawable.ic_home),
        Category("2", "Одежда", R.drawable.ic_person),
        Category("3", "На собачку", R.drawable.ic_dog),
        Category("4", "Ремонт квартиры", R.drawable.ic_leading_element),
        Category("5", "Продукты", R.drawable.ic_eat),
        Category("6", "Спортзал", R.drawable.ic_sport),
        Category("7", "Медицина", R.drawable.ic_tabl)
    )
} 