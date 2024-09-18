package com.ifinancas.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ifinancas.R

@Composable
fun FinanceSelectRow(
    expanded: Boolean,
    tagSelected: String,
    onClick: () -> Unit
) {

    val newTag = when (tagSelected) {
        "receita" -> stringResource(id = R.string.receita)
        "despesa" -> stringResource(R.string.despesa)
        "reserva" -> stringResource(R.string.reserva_minusculo)
        else -> stringResource(id = R.string.receita)
    }

    val tagSelectedFormated = newTag.replaceFirstChar { newTag.first().uppercase() }

    Row(verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .clickable { onClick() }) {
        Text(
            text = tagSelectedFormated + "s",
            fontSize = 15.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
        Icon(
            imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(25.dp)
        )
    }
}