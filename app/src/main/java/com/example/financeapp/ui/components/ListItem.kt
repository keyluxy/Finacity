package com.example.financeapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.financeapp.ui.theme.FinanceAppTheme
import androidx.compose.ui.res.painterResource
import com.example.financeapp.R
import com.example.financeapp.ui.theme.CustomGreen
import com.example.financeapp.ui.theme.CustomLightGrey
import com.example.financeapp.ui.theme.AppIcons
import com.example.financeapp.ui.theme.CustomTextColor
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.graphics.Color

@Composable
fun ListItem(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    leadingContent: @Composable (() -> Unit)? = null,
    content: @Composable () -> Unit,
    trail: @Composable (() -> Unit)? = null
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(enabled = onClick != null) { onClick?.invoke() }
            .padding(vertical = 8.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            leadingContent?.invoke()
            if (leadingContent != null) {
                Spacer(modifier = Modifier.size(16.dp))
            }
            content()
        }
        if (onClick != null) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                trail?.invoke()
                if (trail != null) {
                    Spacer(modifier = Modifier.size(8.dp))
                }
                Icon(painterResource(id = AppIcons.Arrow), contentDescription = "Go to details", modifier = Modifier.size(24.dp), tint = CustomLightGrey)
            }
        } else {
            trail?.invoke()
        }
    }
}

@Composable
fun CategoryListItem(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    leadingContent: @Composable (() -> Unit)? = null,
    content: @Composable () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(enabled = onClick != null) { onClick?.invoke() }
            .padding(vertical = 8.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        leadingContent?.invoke()
        if (leadingContent != null) {
            Spacer(modifier = Modifier.size(16.dp))
        }
        content()
    }
}

@Composable
fun DefaultListItemPreview() {
    FinanceAppTheme {
        Column {
            ListItem(
                leadingContent = {
                    Icon(painterResource(id = R.drawable.ic_expenses), contentDescription = "Home", modifier = Modifier
                        .background(color = CustomLightGrey, shape = CircleShape)
                        .padding(8.dp),
                        tint = Color.Unspecified)
                },
                content = {
                    Column {
                        Text(text = "Заголовок элемента списка", style = MaterialTheme.typography.titleMedium)
                        Text(text = "Подзаголовок элемента списка", style = MaterialTheme.typography.bodySmall)
                    }
                },
                trail = {
                    Text(text = "123 456 ₽", style = MaterialTheme.typography.bodyLarge)
                },
                onClick = { /* Handle click */ }
            )
            ListItem(
                content = {
                    Column {
                        Text(text = "Элемент без иконки и действия", style = MaterialTheme.typography.titleMedium)
                    }
                }
            )
            ListItem(
                leadingContent = {
                    Icon(painterResource(id = R.drawable.ic_articles), contentDescription = "Build", modifier = Modifier
                        .background(color = CustomLightGrey, shape = CircleShape)
                        .padding(8.dp),
                        tint = Color.Unspecified)
                },
                content = {
                    Text(text = "Элемент с иконкой и без подзаголовка", style = MaterialTheme.typography.titleMedium)
                },
                trail = {
                    Text(text = "500 ₽", style = MaterialTheme.typography.bodyLarge)
                },
                onClick = { /* Handle click */ }
            )
        }
    }
} 