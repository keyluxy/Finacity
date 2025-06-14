package com.example.financeapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.financeapp.R
import com.example.financeapp.ui.components.ListItem
import com.example.financeapp.ui.theme.FinanceAppTheme
import com.example.financeapp.ui.theme.CustomGreen
import com.example.financeapp.ui.theme.CustomTextColor
import com.example.financeapp.ui.theme.CustomLightGrey
import com.example.financeapp.ui.theme.AppIcons

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Настройки", color = CustomTextColor) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = CustomGreen,
                    titleContentColor = CustomTextColor
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            LazyColumn {
                item {
                    // Dark Theme setting
                    var darkThemeChecked by remember { mutableStateOf(false) }
                    ListItem(
                        content = {
                            Text(
                                text = "Тёмная тема",
                                style = MaterialTheme.typography.titleMedium,
                                color = CustomTextColor
                            )
                        },
                        trail = {
                            Switch(
                                checked = darkThemeChecked,
                                onCheckedChange = { darkThemeChecked = it })
                        }
                    )
                }
                item {
                    // Primary Color setting
                    ListItem(
                        content = { Text("Основной цвет", color = CustomTextColor) },
                        onClick = { /* Handle click */ }
                    )
                }
                item {
                    // Sounds setting
                    ListItem(
                        content = { Text("Звуки", color = CustomTextColor) },
                        onClick = { /* Handle click */ }
                    )
                }
                item {
                    // Haptics setting
                    ListItem(
                        content = { Text("Тактильный отклик", color = CustomTextColor) },
                        onClick = { /* Handle click */ }
                    )
                }
                item {
                    // Passcode setting
                    ListItem(
                        content = { Text("Код пароль", color = CustomTextColor) },
                        onClick = { /* Handle click */ }
                    )
                }
                item {
                    // Synchronization setting
                    ListItem(
                        content = { Text("Синхронизация", color = CustomTextColor) },
                        onClick = { /* Handle click */ }
                    )
                }
                item {
                    // Language setting
                    ListItem(
                        content = { Text("Язык", color = CustomTextColor) },
                        onClick = { /* Handle click */ }
                    )
                }
                item {
                    // About app setting
                    ListItem(
                        content = { Text("О программе", color = CustomTextColor) },
                        onClick = { /* Handle click */ }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    FinanceAppTheme {
        SettingsScreen()
    }
} 