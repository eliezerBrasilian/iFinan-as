package com.ifinancas.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ifinancas.R
import com.ifinancas.utils.AppUtils.Companion.toDefaultCurrency


@Composable
fun TransactionsHistoryTop(movimentacaoTotalEfetuada: Float) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        /* Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
             Image(
                 painter = painterResource(id = R.drawable.filtro),
                 contentDescription = null,
                 modifier = Modifier
                     .size(25.dp)
             )
         }*/

        Text(
            text = stringResource(R.string.movimenta_o_total_efetuada),
            fontSize = 18.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = toDefaultCurrency(movimentacaoTotalEfetuada),
            fontSize = 18.sp,
            color = Color.White,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

    }

    Spacer(modifier = Modifier.height(20.dp))
}