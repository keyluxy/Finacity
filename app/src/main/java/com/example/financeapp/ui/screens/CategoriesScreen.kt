package com.example.financeapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.financeapp.data.MockData
import com.example.financeapp.ui.components.CategoryListItem
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
import androidx.compose.material3.Divider
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.Alignment

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoriesScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Мои статьи",
                        style = MaterialTheme.typography.titleLarge,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                        color = CustomTextColor
                    )
                },
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
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFECE6F0))
                    .padding(horizontal = 16.dp, vertical = 4.dp)
            ) {
                TextField(
                    value = "",
                    onValueChange = { /*TODO: Handle search input*/ },
                    placeholder = { Text("Найти статью", color = CustomTextColor) },
                    trailingIcon = {
                        Icon(
                            painterResource(id = AppIcons.Search),
                            contentDescription = "Search",
                            tint = CustomTextColor
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )
            }
            Divider(color = CustomLightGrey)

            LazyColumn {
                items(MockData.categories) { category ->
                    CategoryListItem(
                        leadingContent = {
                            Icon(
                                painterResource(id = category.icon!!),
                                contentDescription = category.name,
                                modifier = Modifier.size(24.dp),
                                tint = Color.Unspecified
                            )
                        },
                        content = {
                            Text(
                                text = category.name,
                                style = MaterialTheme.typography.titleMedium,
                                color = CustomTextColor
                            )
                        },
                        onClick = { /* Handle click */ }
                    )
                    Divider(color = CustomLightGrey)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CategoriesScreenPreview() {
    FinanceAppTheme {
        CategoriesScreen()
    }
} 