package com.ifinancas.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.ifinancas.ui.theme.MAINBLUE
import com.ifinancas.ui.theme.dancingScriptFamily

@Composable
fun AppLogoText(textAlign: TextAlign? = null) {
    Text(
        text = "iFinanças",
        fontFamily = dancingScriptFamily,
        fontSize = 23.sp,
        fontWeight = FontWeight.Normal,
        color = MAINBLUE,
        textAlign = textAlign
    )
}