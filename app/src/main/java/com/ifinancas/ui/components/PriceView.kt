package com.ifinancas.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.ifinancas.db.models.TransactionModel.Transaction
import com.ifinancas.utils.AppUtils.Companion.toDefaultCurrency

@Composable
fun PriceView(register: Transaction) {
    Text(
        text = toDefaultCurrency(register.amount),
        fontSize = 14.sp,
        color = Color.Black,
        fontWeight = FontWeight.Bold,
    )
}