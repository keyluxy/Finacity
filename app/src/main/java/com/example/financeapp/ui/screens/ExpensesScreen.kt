package com.example.financeapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.financeapp.ui.components.ListItem
import com.example.financeapp.ui.theme.*
import androidx.compose.ui.res.painterResource
import com.example.financeapp.R
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.example.financeapp.ui.screens.expenses.ExpensesViewModel
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.remember

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpensesScreen(
    onExpenseClick: (String) -> Unit,
    onHistoryClick: () -> Unit,
    onAnalysisClick: () -> Unit,
    onAddExpenseClick: () -> Unit,
    viewModel: ExpensesViewModel = hiltViewModel()
) {
    val expenses by viewModel.expenses.collectAsState()
    val error by viewModel.error.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    if (error != null) {
        LaunchedEffect(error) {
            snackbarHostState.showSnackbar(error!!)
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Расходы сегодня",
                        style = MaterialTheme.typography.titleLarge,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                actions = {
                    IconButton(onClick = onHistoryClick) {
                        Icon(
                            painter = painterResource(id = AppIcons.History),
                            contentDescription = "История",
                            tint = CustomTextColor
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = CustomGreen,
                    titleContentColor = CustomTextColor,
                    actionIconContentColor = CustomTextColor
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onAddExpenseClick() },
                containerColor = CustomGreen,
                contentColor = CustomTextColor,
                shape = CircleShape
            ) {
                Icon(
                    painterResource(id = AppIcons.Plus),
                    contentDescription = "Добавить расход",
                    tint = CustomWhite
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(CustomLightGreen)
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Всего",
                    style = MaterialTheme.typography.bodyLarge,
                    color = CustomTextColor
                )
                Text(
                    text = expenses.sumOf {
                        it.amount.replace(" ", "")
                            .replace("₽", "")
                            .replace(",", ".")
                            .toDoubleOrNull() ?: 0.0
                    }.let { total ->
                        "%.2f ₽".format(total)
                    },
                    style = MaterialTheme.typography.bodyLarge,
                    color = CustomTextColor
                )
            }
            Divider(modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn {
                items(
                    items = expenses,
                    key = { expense -> expense.id }
                ) { expense ->
                    ListItem(
                        leadingContent = {
                            Icon(
                                painterResource(id = R.drawable.ic_expenses),
                                contentDescription = expense.title,
                                modifier = Modifier.size(24.dp),
                                tint = Color.Unspecified
                            )
                        },
                        content = {
                            Column {
                                Text(
                                    text = expense.title,
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = CustomTextColor
                                )
                            }
                        },
                        trail = {
                            Text(
                                text = expense.amount.toString(),
                                style = MaterialTheme.typography.bodyLarge,
                                color = CustomTextColor
                            )
                        },
                        onClick = { onExpenseClick(expense.id) }
                    )
                    Divider()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ExpensesScreenPreview() {
    FinanceAppTheme {
        ExpensesScreen(
            onExpenseClick = { },
            onHistoryClick = { },
            onAnalysisClick = { },
            onAddExpenseClick = { }
        )
    }
} 