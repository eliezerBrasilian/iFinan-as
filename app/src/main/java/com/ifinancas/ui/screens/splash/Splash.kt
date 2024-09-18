package com.ifinancas.ui.screens.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ifinancas.ui.components.AppLogoText
import kotlinx.coroutines.delay

@Composable
fun Splash(nav: NavHostController) {

    val scale by remember {
        mutableStateOf(Animatable(0f))
    }

    LaunchedEffect(key1 = true, block = {
        scale.animateTo(1.5f, animationSpec = tween(800, easing = {
            OvershootInterpolator(12f).getInterpolation(it)
        }))

        delay(1500L)

        nav.navigate("appNavigation") {
            popUpTo("splashNav") { inclusive = true }
            launchSingleTop = true
        }
    })

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Column(
                modifier = Modifier
                    .size(400.dp)
                    .scale(scale.value),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                AppLogoText()
            }
        }
    }
}