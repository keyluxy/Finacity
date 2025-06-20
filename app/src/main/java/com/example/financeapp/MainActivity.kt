package com.example.financeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.financeapp.navigation.Screen
import com.example.financeapp.ui.screens.AccountScreen
import com.example.financeapp.ui.screens.AnalysisScreen
import com.example.financeapp.ui.screens.CategoriesScreen
import com.example.financeapp.ui.screens.ExpenseDetailScreen
import com.example.financeapp.ui.screens.ExpensesScreen
import com.example.financeapp.ui.screens.HistoryScreen
import com.example.financeapp.ui.screens.IncomeDetailScreen
import com.example.financeapp.ui.screens.income.IncomeScreenWrapper
import com.example.financeapp.ui.screens.SettingsScreen
import com.example.financeapp.ui.screens.SplashScreen
import com.example.financeapp.ui.theme.FinanceAppTheme
import androidx.compose.ui.res.painterResource
import com.example.financeapp.R
import com.example.financeapp.ui.theme.CustomLightGreen
import com.example.financeapp.ui.theme.CustomNavigationBackground
import com.example.financeapp.ui.theme.CustomGreen
import com.example.financeapp.ui.theme.CustomTextColor
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import androidx.compose.runtime.SideEffect
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FinanceAppTheme {
                var showSplashScreen by remember { mutableStateOf(true) }

                if (showSplashScreen) {
                    SplashScreen(onTimeout = { showSplashScreen = false })
                } else {
                    AppContent()
                }
            }
        }
    }
}

@Composable
fun AppContent() {
    val navController = rememberNavController()
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(
            color = CustomGreen,
            darkIcons = false
        )
    }
    val items = listOf(
        Screen.Expenses,
        Screen.Income,
        Screen.Account,
        Screen.Categories,
        Screen.Settings
    )
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar(
                containerColor = CustomNavigationBackground
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                items.forEach { screen ->
                    val isSelected = currentDestination?.hierarchy?.any { it.route == screen.route } == true
                    NavigationBarItem(
                        icon = {
                            Icon(
                                painter = painterResource(id = when (screen) {
                                    Screen.Expenses -> R.drawable.ic_expenses
                                    Screen.Income -> R.drawable.ic_income
                                    Screen.Account -> R.drawable.ic_bill
                                    Screen.Categories -> R.drawable.ic_articles
                                    Screen.Settings -> R.drawable.ic_settings
                                }),
                                contentDescription = null,
                                tint = if (isSelected) CustomGreen else CustomTextColor,
                                modifier = if (isSelected) Modifier.background(CustomLightGreen, RoundedCornerShape(50.dp)) else Modifier
                            )
                        },
                        label = {
                            Text(
                                text = screen.title,
                                style = MaterialTheme.typography.labelSmall,
                                color = CustomTextColor,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        },
                        selected = isSelected,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(navController, startDestination = Screen.Expenses.route, Modifier.padding(innerPadding)) {
            composable(Screen.Expenses.route) { ExpensesScreen(onExpenseClick = { expenseId -> navController.navigate("expense_detail/$expenseId") }, onHistoryClick = { navController.navigate("history_screen") }, onAnalysisClick = { navController.navigate("analysis_screen") }, onAddExpenseClick = { navController.navigate("expense_detail") }) }
            composable(Screen.Income.route) { IncomeScreenWrapper(onIncomeClick = { incomeId -> navController.navigate("income_detail/$incomeId") }) }
            composable(Screen.Account.route) { AccountScreen() }
            composable(Screen.Categories.route) { CategoriesScreen() }
            composable(Screen.Settings.route) { SettingsScreen() }
            composable("expense_detail/{expenseId}") { backStackEntry ->
                val expenseId = backStackEntry.arguments?.getString("expenseId")
                ExpenseDetailScreen(expenseId = expenseId, onBackClick = { navController.popBackStack() })
            }
            composable("expense_detail") { ExpenseDetailScreen(onBackClick = { navController.popBackStack() }) }
            composable("income_detail/{incomeId}") { backStackEntry ->
                val incomeId = backStackEntry.arguments?.getString("incomeId")
                IncomeDetailScreen(incomeId = incomeId, onBackClick = { navController.popBackStack() })
            }
            composable("history_screen") { HistoryScreen(onBackClick = { navController.popBackStack() }, onAnalysisClick = { navController.navigate("analysis_screen") }) }
            composable("analysis_screen") { AnalysisScreen(onBackClick = { navController.popBackStack() }) }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FinanceAppTheme {
        AppContent()
    }
}