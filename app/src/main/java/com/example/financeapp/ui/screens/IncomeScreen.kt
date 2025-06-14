package com.example.financeapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.financeapp.data.MockData
import com.example.financeapp.ui.components.ListItem
import com.example.financeapp.ui.theme.FinanceAppTheme
import com.example.financeapp.ui.theme.AppIcons
import com.example.financeapp.ui.theme.CustomWhite
import com.example.financeapp.ui.theme.CustomGreen
import androidx.compose.ui.res.painterResource
import com.example.financeapp.R
import com.example.financeapp.ui.theme.CustomTextColor
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.graphics.Color
import com.example.financeapp.ui.theme.CustomLightGrey
import com.example.financeapp.ui.theme.CustomLightGreen
import androidx.compose.material3.Divider

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IncomeScreen(onIncomeClick: (String) -> Unit) {
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
                actions = {
                    IconButton(onClick = { /*TODO: Handle History click*/ }) {
                        Icon(
                            painterResource(id = AppIcons.History),
                            contentDescription = "История",
                            tint = CustomTextColor
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = CustomGreen,
                    titleContentColor = CustomTextColor
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /*TODO: Handle Add button click*/ },
                containerColor = CustomGreen,
                contentColor = CustomTextColor,
                shape = CircleShape
            ) {
                Icon(
                    painterResource(id = AppIcons.Plus),
                    contentDescription = "Add income",
                    tint = CustomWhite
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        )
        {
            ListItem(
                modifier = Modifier.background(CustomLightGreen),
                content = {
                    Text(
                        text = "Всего",
                        style = MaterialTheme.typography.titleMedium,
                        color = CustomTextColor
                    )
                },
                trail = {
                    Text(
                        text = "600 000 ₽",
                        style = MaterialTheme.typography.titleMedium,
                        color = CustomTextColor
                    )
                },
                onClick = null // No click action for "Всего"
            )
            Divider(color = CustomLightGrey, modifier = Modifier.fillMaxWidth())
            LazyColumn {
                items(MockData.income) { income ->
                    ListItem(
                        content = {
                            Column {
                                Text(
                                    text = income.title,
                                    style = MaterialTheme.typography.titleMedium,
                                    color = CustomTextColor
                                )
                            }
                        },
                        trail = {
                            Text(
                                text = income.amount,
                                style = MaterialTheme.typography.bodyLarge,
                                color = CustomTextColor
                            )
                        },
                        onClick = { onIncomeClick(income.id) }
                    )
                    Divider(color = CustomLightGrey, modifier = Modifier.fillMaxWidth())
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun IncomeScreenPreview() {
    FinanceAppTheme {
        IncomeScreen(onIncomeClick = { /* Do nothing for preview */ })
    }
} 