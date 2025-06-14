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
import androidx.compose.foundation.layout.width
import com.example.financeapp.ui.theme.CustomLightGreen
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.example.financeapp.ui.theme.CustomLightGrey
import androidx.compose.material3.Divider

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreen(
    onBackClick: () -> Unit,
    onAnalysisClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Моя история",
                        style = MaterialTheme.typography.titleLarge,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
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
                    IconButton(onClick = onAnalysisClick) {
                        Icon(
                            painterResource(id = AppIcons.Analyze),
                            contentDescription = "Анализ",
                            tint = CustomTextColor
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = CustomGreen,
                    titleContentColor = CustomTextColor,
                    navigationIconContentColor = CustomTextColor,
                    actionIconContentColor = CustomTextColor
                )
            )
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
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Начало",
                    style = MaterialTheme.typography.bodyLarge,
                    color = CustomTextColor
                )
                Text(
                    text = "Февраль 2025",
                    style = MaterialTheme.typography.bodyLarge,
                    color = CustomTextColor
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(CustomLightGreen)
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Конец",
                    style = MaterialTheme.typography.bodyLarge,
                    color = CustomTextColor
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "23:41",
                        style = MaterialTheme.typography.bodyLarge,
                        color = CustomTextColor
                    )
                    Spacer(modifier = Modifier.width(4.dp))

                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(CustomLightGreen)
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Сумма",
                    style = MaterialTheme.typography.bodyLarge,
                    color = CustomTextColor
                )
                Text(
                    text = "125 868 ₽",
                    style = MaterialTheme.typography.bodyLarge,
                    color = CustomTextColor
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            LazyColumn {
                items(MockData.transactions) { transaction ->
                    ListItem(
                        leadingContent = {
                            Icon(
                                painterResource(id = transaction.icon!!),
                                contentDescription = transaction.title,
                                modifier = Modifier
                                    .size(24.dp),
                                tint = Color.Unspecified
                            )
                        },
                        content = {
                            Column {
                                Text(
                                    text = transaction.title,
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = CustomTextColor
                                )
                                Text(
                                    text = transaction.date,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = CustomTextColor
                                )
                            }
                        },
                        trail = {
                            Text(
                                text = transaction.amount,
                                style = MaterialTheme.typography.bodyLarge,
                                color = CustomTextColor
                            )
                        },
                        onClick = { /* Handle click */ }
                    )
                    Divider()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HistoryScreenPreview() {
    FinanceAppTheme {
        HistoryScreen(onBackClick = {}, onAnalysisClick = {})
    }
} 