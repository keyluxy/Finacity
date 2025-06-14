package com.example.financeapp.data.model

data class Account(
    val id: String,
    val name: String, // например, "Сбербанк"
    val balance: String, // например, "-670 000 ₽"
    val currency: String, // например, "Российский рубль ₽"
    val icon: Int? = null // Иконка для ведущего изображения
) 