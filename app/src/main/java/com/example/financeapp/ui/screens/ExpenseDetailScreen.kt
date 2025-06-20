package com.example.financeapp.ui.screens

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.financeapp.data.MockData
import com.example.financeapp.data.model.ExpenseDetailed
import com.example.financeapp.ui.components.ListItem
import com.example.financeapp.ui.theme.FinanceAppTheme
import com.example.financeapp.ui.theme.CustomWhite
import com.example.financeapp.ui.theme.AppIcons
import androidx.compose.ui.res.painterResource
import com.example.financeapp.R
import com.example.financeapp.ui.theme.CustomGreen
import com.example.financeapp.ui.theme.CustomTextColor
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.Divider
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.width
import androidx.compose.ui.Alignment
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpenseDetailScreen(expenseId: String? = null, onBackClick: () -> Unit) {
    val expense = if (expenseId != null) {
        MockData.expensesDetailed.find { it.id == expenseId } ?: MockData.expensesDetailed.first()
    } else {
        // Default empty expense for adding new one
        ExpenseDetailed(
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier
                    .height(28.dp)
                    .fillMaxWidth()
                    .background(Color(0xFF1D1B20)),
                title = {
                    Text(
                        text = "Мои расходы",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.W400,
                            color = CustomWhite
                        ),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            painterResource(id = AppIcons.ic_canel),
                            contentDescription = "Отмена",
                            tint = CustomWhite
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /*TODO: Save Expense*/ }) {
                        Icon(
                            painterResource(id = AppIcons.Ok),
                            contentDescription = "Сохранить",
                            tint = CustomWhite
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF1D1B20),
                    titleContentColor = CustomWhite,
                    navigationIconContentColor = CustomWhite,
                    actionIconContentColor = CustomWhite
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
                trail = { Text("Сбербанк", color = CustomTextColor, fontSize = 16.sp) },
                onClick = { /* Handle click */ }
            )
            Divider()
            ListItem(
                content = { Text("Статья") },
                trail = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Ремонт", color = CustomTextColor, fontSize = 16.sp)
                        Spacer(modifier = Modifier.width(4.dp))

                    }
                },
                onClick = { /* Handle click */ }
            )
            Divider()
            ListItem(
                content = { Text("Сумма") },
                trail = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("25 270 ₽", fontSize = 16.sp, color = CustomTextColor)
                        Spacer(modifier = Modifier.width(4.dp))

                    }
                },
                onClick = null
            )
            Divider()
            ListItem(
                content = { Text("Дата") },
                trail = { Text("25.02.2025", fontSize = 16.sp, color = CustomTextColor) },
                onClick = null
            )
            Divider()
            ListItem(
                content = { Text("Время") },
                trail = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("23:41", fontSize = 16.sp, color = CustomTextColor)
                        Spacer(modifier = Modifier.width(4.dp))
                    }
                },
                onClick = null
            )
            Divider()
            ListItem(
                content = { Text("Ремонт - фурнитура для дверей") },
                onClick = null
            )
            Divider()

            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { /*TODO: Handle Delete Expense*/ },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFE46962),
                    contentColor = CustomTextColor
                ),
                shape = RoundedCornerShape(50.dp)
            ) {
                Text("Удалить расход", color = CustomWhite)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ExpenseDetailScreenPreview() {
    FinanceAppTheme {
        ExpenseDetailScreen("1", onBackClick = {})
    }
}

@Preview(showBackground = true)
@Composable
fun NewExpenseDetailScreenPreview() {
    FinanceAppTheme {
        ExpenseDetailScreen(null, onBackClick = {})
    }
}