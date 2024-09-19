package com.br.ifinancas.ui.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import com.br.ifinancas.R
import com.braziliancurrencyvisualtransformation.BrazilianCurrencyVisualTransformation

@Composable
fun RegisterValueInput(
    valueInput: String,
    onValueChange: (value: String) -> Unit,
    tag: String?,
    isFocused: Boolean,
    onFocusChange: (focused: Boolean) -> Unit,
    focusRequester: FocusRequester
) {

    val newTag =
        when (tag) {
            "receita" -> stringResource(id = R.string.receita)
            "despesa" -> stringResource(R.string.despesa)
            "reserva" -> stringResource(R.string.reserva_minusculo)
            else -> stringResource(id = R.string.receita)
        }


    OutlinedTextField(value = valueInput, onValueChange = onValueChange,
        visualTransformation = BrazilianCurrencyVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        singleLine = true,
        label = {
            Text(
                stringResource(R.string.valor_da, newTag),
                color = Color.White,
                fontSize = 10.sp
            )
        },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.Transparent,
            focusedContainerColor = Color.White,
            cursorColor = Color.Yellow,
            unfocusedPlaceholderColor = Color.White
        ),
        textStyle = TextStyle(
            color = if (isFocused) Color.Black else Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        ),
        modifier = Modifier
            .onFocusChanged { onFocusChange(it.isFocused) }
            .focusRequester(focusRequester)
    )
}