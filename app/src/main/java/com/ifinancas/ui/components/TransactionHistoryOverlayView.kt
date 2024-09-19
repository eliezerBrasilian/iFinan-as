package com.br.ifinancas.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.br.ifinancas.R
import com.br.ifinancas.db.models.TransactionModel.Transaction
import com.br.ifinancas.ui.screens.home.widgets.MonthView


@Composable
fun TransactionHistoryOverlayView(
    sortRegistersList: List<Transaction>,
    currentDate: String,
    incrementMonth: () -> Unit,
    decrementMonth: () -> Unit,
    handleDeleteRegister: (registerId: Int) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White, RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
    ) {
        DragableView()

        MonthView(decrementMonth, currentDate, incrementMonth)

        if (sortRegistersList.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 20.dp)
            ) {
                Text(
                    text = stringResource(R.string.nenhum_registro_encontrado),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
        LazyColumn {
            items(sortRegistersList) {
                TransactionItem(it, handleDeleteRegister)
            }
        }

    }
}

