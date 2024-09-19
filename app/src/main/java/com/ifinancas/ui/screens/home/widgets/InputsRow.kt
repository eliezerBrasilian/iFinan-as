package com.br.ifinancas.ui.screens.home.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.OutlinedTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.br.ifinancas.ui.screens.home.HomeViewModel
import com.frajola.BrazilianCurrencyVisualTransformation


@Composable
fun InputsRow(
    homeViewModel: HomeViewModel,
    firstInputValue: String,
    onChangeFirstInput: (String) -> Unit,
    firstInputPlaceholder: String,
    secondInputPlaceholder: String,
    secondInputValue: String,
    onChangeSecondInput: (String) -> Unit,
    onClickButton: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            value = firstInputValue,
            onValueChange = onChangeFirstInput,
            modifier = Modifier.fillMaxWidth(0.2f),
            placeholder = { Text(text = firstInputPlaceholder, fontSize = 13.sp) },
        )
        OutlinedTextField(
            value = secondInputValue,
            onValueChange = onChangeSecondInput,
            modifier = Modifier.fillMaxWidth(0.4f),
            placeholder = { Text(text = secondInputPlaceholder, fontSize = 13.sp) },
            visualTransformation = BrazilianCurrencyVisualTransformation()
        )
        Button(onClick = onClickButton) {
            Text(text = "Salvar")
        }
    }
}