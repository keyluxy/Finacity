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
import androidx.compose.ui.unit.sp
import com.example.financeapp.ui.components.ListItem
import com.example.financeapp.ui.theme.*
import androidx.compose.ui.res.painterResource
import com.example.financeapp.R
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.graphics.Color
import com.example.financeapp.ui.screens.income.IncomeViewModel
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.remember

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IncomeScreen(
    onIncomeClick: (String) -> Unit,
    onHistoryClick: () -> Unit,
    onAddIncomeClick: () -> Unit,
    viewModel: IncomeViewModel = hiltViewModel()
) {
    val incomes by viewModel.incomes.collectAsState()
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
                        text = "Доходы сегодня",
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
                onClick = onAddIncomeClick,
                containerColor = CustomGreen,
                contentColor = CustomTextColor,
                shape = CircleShape
            ) {
                Icon(
                    painterResource(id = AppIcons.Plus),
                    contentDescription = "Добавить доход",
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
                    text = incomes.sumOf {
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
                    items = incomes,
                    key = { income -> income.id }
                ) { income ->
                    ListItem(
                        leadingContent = {
                            if (!income.emoji.isNullOrBlank()) {
                                Text(
                                    text = income.emoji,
                                    fontSize = 24.sp,
                                    modifier = Modifier.size(32.dp)
                                )
                            } else {
                                Icon(
                                    painterResource(id = R.drawable.ic_income),
                                    contentDescription = income.title,
                                    modifier = Modifier.size(24.dp),
                                    tint = Color.Unspecified
                                )
                            }
                        },
                        content = {
                            Column {
                                Text(
                                    text = income.title,
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = CustomTextColor
                                )
                            }
                        },
                        trail = {
                            Text(
                                text = income.amount.toString(),
                                style = MaterialTheme.typography.bodyLarge,
                                color = CustomTextColor
                            )
                        },
                        onClick = { onIncomeClick(income.id) }
                    )
                    Divider()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun IncomeScreenPreview() {
    FinanceAppTheme {
        IncomeScreen(onIncomeClick = { }, onHistoryClick = { }, onAddIncomeClick = { })
    }
} 