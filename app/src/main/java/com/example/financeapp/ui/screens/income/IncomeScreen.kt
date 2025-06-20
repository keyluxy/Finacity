package com.example.financeapp.ui.screens.income

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.financeapp.ui.screens.IncomeScreen

@Composable
fun IncomeScreenWrapper(onIncomeClick: (String) -> Unit) {
    IncomeScreen(onIncomeClick = onIncomeClick, viewModel = hiltViewModel())
} 