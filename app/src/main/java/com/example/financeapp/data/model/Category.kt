package com.example.financeapp.data.model

data class Category(
    val id: String,
    val name: String, // например, "Аренда квартиры"
    val icon: Int? = null // Иконка для ведущего изображения
) 