package com.example.financeapp.ui.screens.expenses

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financeapp.data.model.Expense
import com.example.financeapp.data.repository.ExpenseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.ZoneId
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ExpensesViewModel @Inject constructor(
    private val expenseRepository: ExpenseRepository
) : ViewModel() {

    private val _expenses = MutableStateFlow<List<Expense>>(emptyList())
    val expenses: StateFlow<List<Expense>> = _expenses

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    private val token = "fVkPLrT88G3QIadT9IfB9hdH" // TODO: вынести в secure storage

    init {
        loadExpenses()
    }

    private fun getWideTimestamps(): Pair<Long, Long> {
        val from = LocalDate.of(2025, 1, 1).atStartOfDay(ZoneId.systemDefault()).toEpochSecond()
        val to = LocalDate.of(2025, 12, 31).atStartOfDay(ZoneId.systemDefault()).toEpochSecond()
        return from to to
    }

    fun loadExpenses() {
        viewModelScope.launch {
            try {
                val (from, to) = getWideTimestamps()
                Log.d("ExpensesViewModel", "from: $from, to: $to")
                _expenses.value = withContext(Dispatchers.IO) {
                    expenseRepository.getExpenses(token, from, to)
                }
            } catch (e: Exception) {
                _error.value = e.message ?: "Ошибка загрузки расходов"
            }
        }
    }
} 