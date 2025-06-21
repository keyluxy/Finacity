package com.example.financeapp.ui.screens.income

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financeapp.data.model.Income
import com.example.financeapp.data.repository.IncomeRepository
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
class IncomeViewModel @Inject constructor(
    private val incomeRepository: IncomeRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<Income>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<Income>>> = _uiState

    private val token = "fVkPLrT88G3QIadT9IfB9hdH" // TODO: вынести в secure storage

    init {
        loadIncomes()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getWideTimestamps(): Pair<Long, Long> {
        val from = LocalDate.of(2025, 1, 1).atStartOfDay(ZoneId.systemDefault()).toEpochSecond()
        val to = LocalDate.of(2025, 12, 31).atStartOfDay(ZoneId.systemDefault()).toEpochSecond()
        return from to to
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun loadIncomes() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val (from, to) = getWideTimestamps()
                Log.d("IncomeViewModel", "Загрузка доходов. from: $from, to: $to")
                val incomes = withContext(Dispatchers.IO) {
                    incomeRepository.getIncomes(token, from, to)
                }
                _uiState.value = UiState.Success(incomes)
                Log.d("IncomeViewModel", "Загружено ${_uiState.value} доходов")
            } catch (e: Exception) {
                val errorMessage = e.message ?: "Ошибка загрузки доходов"
                _uiState.value = UiState.Error(errorMessage)
                Log.e("IncomeViewModel", "Ошибка при загрузке доходов", e)
            }
        }
    }
} 