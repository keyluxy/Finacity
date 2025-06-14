package com.example.financeapp.data.model

import androidx.compose.ui.graphics.vector.ImageVector

data class Transaction(
    val id: String,
    val title: String, // Например, "Ремонт квартиры" или "Зарплата"
    val subtitle: String? = null, // Добавляем поле для подзаголовка
    val amount: String, // Например, "100 000 ₽"
    val percentage: String, // Добавляем поле для процента
    val date: String, // Например, "25.02.2025"
    val time: String, // Например, "23:41"
    val icon: Int? = null // Иконка для категории или типа операции
) 