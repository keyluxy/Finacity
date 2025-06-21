package com.example.financeapp.ui.screens.income

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financeapp.data.model.Income
import com.example.financeapp.data.repository.IncomeRepository
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

    private val _incomes = MutableStateFlow<List<Income>>(emptyList())
    val incomes: StateFlow<List<Income>> = _incomes

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

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
            try {
                val (from, to) = getWideTimestamps()
                Log.d("IncomeViewModel", "Загрузка доходов. from: $from, to: $to")
                _incomes.value = withContext(Dispatchers.IO) {
                    incomeRepository.getIncomes(token, from, to)
                }
                Log.d("IncomeViewModel", "Загружено ${_incomes.value.size} доходов")
            } catch (e: Exception) {
                _error.value = e.message ?: "Ошибка загрузки доходов"
                Log.e("IncomeViewModel", "Ошибка при загрузке доходов", e)
            }
        }
    }
} 