package com.example.financeapp.ui.screens.expenses

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financeapp.data.model.Expense
import com.example.financeapp.data.repository.ExpenseRepository
import com.example.financeapp.ui.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.ZoneId
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class ExpensesViewModel @Inject constructor(
    private val expenseRepository: ExpenseRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<Expense>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<Expense>>> = _uiState

    private val token = "fVkPLrT88G3QIadT9IfB9hdH" // TODO: вынести в secure storage

    init {
        loadExpenses()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getWideTimestamps(): Pair<Long, Long> {
        val from = LocalDate.of(2025, 1, 1).atStartOfDay(ZoneId.systemDefault()).toEpochSecond()
        val to = LocalDate.of(2025, 12, 31).atStartOfDay(ZoneId.systemDefault()).toEpochSecond()
        return from to to
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun loadExpenses() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val (from, to) = getWideTimestamps()
                Log.d("ExpensesViewModel", "from: $from, to: $to")
                val expenses = withContext(Dispatchers.IO) {
                    expenseRepository.getExpenses(token, from, to)
                }
                _uiState.value = UiState.Success(expenses)
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Ошибка загрузки расходов")
            }
        }
    }
} 