package com.br.ifinancas.ui.screens.home.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.br.ifinancas.ui.components.SaldoComponent
import com.br.ifinancas.ui.theme.MAINPURPLE

@Composable
fun HomeBlueTop(
    toogleMonthListVisibility: () -> Unit = {},
    toogleMenuListVisibility: () -> Unit = {},
    monthListVisible: Boolean = false,
    menuListVisibile: Boolean = false,
    monthSelected: String,
    balanceIsVisible: Boolean,
    toogleBalanceVisibility: () -> Unit,
    balance: Float
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MAINPURPLE)
            .padding(15.dp)
    ) {
        MonthSelectable(toogleMonthListVisibility, monthListVisible, monthSelected)
        Spacer(modifier = Modifier.height(10.dp))
        SaldoComponent(balance, balanceIsVisible, toogleBalanceVisibility)
        Spacer(modifier = Modifier.height(10.dp))
    }
}


