package com.example.financeapp.ui.screens.expenses

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ExpensesScreen(
    viewModel: ExpensesViewModel = hiltViewModel()
) {
    val expenses by viewModel.expenses.collectAsState()

    LazyColumn {
        items(expenses) { expense ->
            Text(text = "${expense.title}: ${expense.amount}")
        }
    }
} 