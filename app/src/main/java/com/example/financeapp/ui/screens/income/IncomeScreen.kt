package com.example.financeapp.ui.screens.income

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.financeapp.ui.screens.IncomeScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun IncomeScreenWrapper(
    onIncomeClick: (String) -> Unit,
    onHistoryClick: () -> Unit,
    onAddIncomeClick: () -> Unit
) {
    IncomeScreen(
        onIncomeClick = onIncomeClick,
        onHistoryClick = onHistoryClick,
        onAddIncomeClick = onAddIncomeClick,
        viewModel = hiltViewModel()
    )
} 