package com.example.financeapp.ui.screens.expenses

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
import javax.inject.Inject

@HiltViewModel
class ExpensesViewModel @Inject constructor(
    private val expenseRepository: ExpenseRepository
) : ViewModel() {

    private val _expenses = MutableStateFlow<List<Expense>>(emptyList())
    val expenses: StateFlow<List<Expense>> = _expenses

    private val token = "fVkPLrT88G3QIadT9IfB9hdH" // TODO: вынести в secure storage

    init {
        loadExpenses()
    }

    private fun getTodayTimestamps(): Pair<Long, Long> {
        val now = LocalDate.now()
        val from = now.atStartOfDay(ZoneId.systemDefault()).toEpochSecond()
        val to = now.plusDays(1).atStartOfDay(ZoneId.systemDefault()).toEpochSecond() - 1
        return from to to
    }

    private fun loadExpenses() {
        viewModelScope.launch {
            val (from, to) = getTodayTimestamps()
            _expenses.value = expenseRepository.getExpenses(token, from, to)
        }
    }
} 