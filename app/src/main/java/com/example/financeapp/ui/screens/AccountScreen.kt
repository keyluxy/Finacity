package com.example.financeapp.ui.screens

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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.material3.Divider
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.width
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountScreen() {
    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        Text(
                            text = "Мой счет",
                            style = MaterialTheme.typography.titleLarge,
                            color = CustomTextColor
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /*TODO: Handle Edit click*/ }) {
                        Icon(
                            painterResource(id = AppIcons.Edit),
                            contentDescription = "Edit account",
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
                onClick = { /*TODO: Handle Add button click*/ },
                containerColor = CustomGreen,
                contentColor = CustomTextColor,
                shape = CircleShape
            ) {
                Icon(
                    painterResource(id = AppIcons.Plus),
                    contentDescription = "Add account",
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
                modifier = Modifier.background(Color(0xFFD4FAE6)),
                leadingContent = {
                    Icon(
                        painterResource(id = AppIcons.Dollar),
                        contentDescription = "Баланс",
                        modifier = Modifier.size(24.dp),
                        tint = Color.Unspecified // Для иконок из res/drawable часто Color.Unspecified
                    )
                },
                content = { Text("Баланс") },
                trail = { Text("-670 000 ₽", color = CustomTextColor, fontSize = 16.sp) },
                onClick = { /* Handle click */ }
            )
            Divider(color = CustomLightGrey)
            ListItem(
                modifier = Modifier.background(Color(0xFFD4FAE6)),
                content = { Text("Валюта") },
                trail = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("₽", color = CustomTextColor, fontSize = 16.sp)
                        Spacer(modifier = Modifier.width(4.dp))
                        Icon(
                            painterResource(id = AppIcons.Arrow),
                            contentDescription = "Select currency",
                            modifier = Modifier.size(24.dp),
                            tint = CustomLightGrey
                        )
                    }
                },
                onClick = { showBottomSheet = true }
            )
            Divider(color = CustomLightGrey, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(16.dp))
            Image(
                painter = painterResource(id = AppIcons.Diagram),
                contentDescription = "Диаграмма динамики счета",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp) // Adjust height as needed
                    .padding(horizontal = 16.dp),
                contentScale = ContentScale.FillWidth
            )
        }
    }

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = { showBottomSheet = false },
            sheetState = sheetState,
            containerColor = CustomWhite,
            shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Box(
                    modifier = Modifier
                        .width(48.dp)
                        .height(4.dp)
                        .background(CustomLightGrey, RoundedCornerShape(2.dp))
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 8.dp, bottom = 16.dp)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { /*TODO: Handle Ruble selection*/ showBottomSheet = false }
                        .padding(vertical = 8.dp, horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painterResource(id = AppIcons.Ruble),
                        contentDescription = "Российский рубль",
                        modifier = Modifier.size(24.dp),
                        tint = Color.Unspecified
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text("Российский рубль ₽", color = CustomTextColor, fontSize = 16.sp)
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(
                        painterResource(id = AppIcons.Arrow),
                        contentDescription = "Select",
                        modifier = Modifier.size(24.dp),
                        tint = CustomLightGrey
                    )
                }
                Divider(color = CustomLightGrey, modifier = Modifier.fillMaxWidth())
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { /*TODO: Handle Dollar selection*/ showBottomSheet = false }
                        .padding(vertical = 8.dp, horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painterResource(id = AppIcons.DollarIcon),
                        contentDescription = "Американский доллар",
                        modifier = Modifier.size(24.dp),
                        tint = Color.Unspecified
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text("Американский доллар $", color = CustomTextColor, fontSize = 16.sp)
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(
                        painterResource(id = AppIcons.Arrow),
                        contentDescription = "Select",
                        modifier = Modifier.size(24.dp),
                        tint = CustomLightGrey
                    )
                }
                Divider(color = CustomLightGrey, modifier = Modifier.fillMaxWidth())
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { /*TODO: Handle Euro selection*/ showBottomSheet = false }
                        .padding(vertical = 8.dp, horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painterResource(id = AppIcons.Euro),
                        contentDescription = "Евро",
                        modifier = Modifier.size(24.dp),
                        tint = Color.Unspecified
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text("Евро", color = CustomTextColor, fontSize = 16.sp)
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(
                        painterResource(id = AppIcons.Arrow),
                        contentDescription = "Select",
                        modifier = Modifier.size(24.dp),
                        tint = CustomLightGrey
                    )
                }
                Divider(color = CustomLightGrey, modifier = Modifier.fillMaxWidth())

                Button(
                    onClick = { showBottomSheet = false },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE46962), contentColor = CustomWhite),
                    shape = RoundedCornerShape(0.dp)
                ) {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically) {
                        Icon(painterResource(id = AppIcons.Cancel), contentDescription = "Отмена", tint = CustomWhite)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Отмена", color = CustomWhite)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AccountScreenPreview() {
    FinanceAppTheme {
        AccountScreen()
    }
} 