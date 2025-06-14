package com.example.financeapp.ui.screens

import androidx.compose.foundation.Canvas
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
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
import com.example.financeapp.ui.theme.CustomLightGrey
import androidx.compose.foundation.Image
import androidx.compose.material3.Divider
import androidx.compose.foundation.shape.RoundedCornerShape

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnalysisScreen(onBackClick: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        Text(
                            text = "Анализ",
                            style = MaterialTheme.typography.titleLarge,
                            color = CustomTextColor,
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
                    // Add actions if needed for analysis screen
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = CustomWhite,
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
            ListItem(
                modifier = Modifier.background(CustomWhite),
                content = {
                    Text(
                        text = "Период: начало",
                        style = MaterialTheme.typography.bodyLarge,
                        color = CustomTextColor
                    )
                },
                trail = {
                    Text(
                        text = "Февраль 2025",
                        style = MaterialTheme.typography.bodyLarge,
                        color = CustomTextColor,
                        modifier = Modifier
                            .background(Color(0xFF2AE881), RoundedCornerShape(16.dp))
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            )
            Divider(color = CustomLightGrey, modifier = Modifier.fillMaxWidth())
            ListItem(
                modifier = Modifier.background(CustomWhite),
                content = {
                    Text(
                        text = "Период: конец",
                        style = MaterialTheme.typography.bodyLarge,
                        color = CustomTextColor
                    )
                },
                trail = {
                    Text(
                        text = "Март 2025",
                        style = MaterialTheme.typography.bodyLarge,
                        color = CustomTextColor,
                        modifier = Modifier
                            .background(Color(0xFF2AE881), RoundedCornerShape(16.dp))
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            )
            Divider(color = CustomLightGrey, modifier = Modifier.fillMaxWidth())
            ListItem(
                modifier = Modifier.background(CustomWhite),
                content = {
                    Text(
                        text = "Сумма",
                        style = MaterialTheme.typography.bodyLarge,
                        color = CustomTextColor
                    )
                },
                trail = {
                    Text(
                        text = "125 868 ₽",
                        style = MaterialTheme.typography.bodyLarge,
                        color = CustomTextColor
                    )
                }
            )
            Divider(color = CustomLightGrey, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_crug_diagrama),
                    contentDescription = "Круговая диаграмма",
                    modifier = Modifier.size(200.dp)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn {
                items(MockData.transactions) { transaction ->
                    ListItem(
                        modifier = Modifier.background(CustomWhite),
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
                                    style = MaterialTheme.typography.titleMedium,
                                    color = CustomTextColor
                                )
                                transaction.subtitle?.let { subtitle ->
                                    Text(
                                        text = subtitle,
                                        style = MaterialTheme.typography.bodySmall,
                                        color = CustomTextColor
                                    )
                                }
                            }
                        },
                        trail = {
                            Column(horizontalAlignment = Alignment.End) {
                                Text(
                                    text = transaction.percentage,
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = CustomTextColor
                                )
                                Text(
                                    text = transaction.amount,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = CustomTextColor
                                )
                            }
                        },
                        onClick = { /*TODO: Handle item click*/ }
                    )
                    Divider(color = CustomLightGrey, modifier = Modifier.fillMaxWidth())
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AnalysisScreenPreview() {
    FinanceAppTheme {
        AnalysisScreen(onBackClick = {})
    }
} 