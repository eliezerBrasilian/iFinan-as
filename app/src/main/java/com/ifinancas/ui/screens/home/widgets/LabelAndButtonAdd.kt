package com.br.ifinancas.ui.screens.home.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.br.ifinancas.ui.screens.home.HomeUiState
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.CaretDown
import compose.icons.fontawesomeicons.solid.CaretUp

@Composable
fun LabelAndButtonAdd(
    homeUiState: State<HomeUiState>,
    label: String,
    onClick: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = label)
        Button(onClick = onClick) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Adicionar ")
                Icon(
                    imageVector = if (homeUiState.value.inputFiiNameIsVisible)
                        FontAwesomeIcons.Solid.CaretUp
                    else FontAwesomeIcons.Solid.CaretDown,
                    contentDescription = null,
                    tint = Color.White, modifier = Modifier.size(10.dp)
                )
            }
        }
    }
}