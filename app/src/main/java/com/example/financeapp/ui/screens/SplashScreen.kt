package com.example.financeapp.ui.screens

import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.financeapp.R
import com.example.financeapp.ui.theme.FinanceAppTheme
import kotlinx.coroutines.delay
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.ui.graphics.graphicsLayer
import com.example.financeapp.ui.theme.AppIcons
import androidx.compose.runtime.getValue

@Composable
fun SplashScreen(onTimeout: () -> Unit) {
    val infiniteTransition = rememberInfiniteTransition(label = "Rotation Transition")
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "Rotation Animation"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primaryContainer),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = AppIcons.Dollar),
            contentDescription = "Dollar Icon",
            modifier = Modifier
                .size(200.dp)
                .graphicsLayer { rotationZ = rotation }
        )
    }

    LaunchedEffect(key1 = true) {
        delay(2000L)
        onTimeout()
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    FinanceAppTheme {
        SplashScreen(onTimeout = {})
    }
} 