package com.example.financeapp.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.financeapp.data.MockData
import com.example.financeapp.data.model.IncomeDetailed
import com.example.financeapp.ui.components.ListItem
import com.example.financeapp.ui.theme.FinanceAppTheme
import com.example.financeapp.ui.theme.CustomWhite
import com.example.financeapp.ui.theme.AppIcons
import androidx.compose.ui.res.painterResource
import com.example.financeapp.R
import com.example.financeapp.ui.theme.CustomGreen
import com.example.financeapp.ui.theme.CustomTextColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IncomeDetailScreen(incomeId: String?, onBackClick: () -> Unit) {
    val income =
        MockData.incomeDetailed.find { it.id == incomeId } ?: MockData.incomeDetailed.first()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        Text(
                            text = "Доходы сегодня",
                            style = MaterialTheme.typography.titleLarge,
                            color = CustomTextColor
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            painterResource(id = AppIcons.back),
                            contentDescription = "Назад",
                            tint = CustomTextColor
                        )
                    }
                },
                actions = {
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = CustomGreen,
                    titleContentColor = CustomTextColor,
                    navigationIconContentColor = CustomTextColor
                )
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            ListItem(
                content = { Text("Счет") },
                trail = { Text(income.account) },
                onClick = { /* Handle click */ }
            )
            ListItem(
                content = { Text("Категория") },
                trail = { Text(income.category) },
                onClick = { /* Handle click */ }
            )
            ListItem(
                content = { Text("Сумма") },
                trail = { Text(income.amount) },
                onClick = { /* Handle click */ }
            )
            ListItem(
                content = { Text("Дата") },
                trail = { Text(income.date) },
                onClick = { /* Handle click */ }
            )
            ListItem(
                content = { Text("Время") },
                trail = { Text(income.time) },
                onClick = { /* Handle click */ }
            )
            income.description?.let {
                ListItem(
                    content = { Text("Описание") },
                    trail = { Text(it) },
                    onClick = { /* Handle click */ }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { /*TODO: Handle Delete Income*/ },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFE46962),
                    contentColor = CustomTextColor
                ),
            ) {
                Text("Удалить доход")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun IncomeDetailScreenPreview() {
    FinanceAppTheme {
        IncomeDetailScreen("1", onBackClick = {})
    }
} 