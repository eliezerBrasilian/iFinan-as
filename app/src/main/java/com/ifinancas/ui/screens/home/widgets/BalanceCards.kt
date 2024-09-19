package com.br.ifinancas.ui.screens.home.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.br.ifinancas.R
import com.br.ifinancas.data.enums.Tags
import com.br.ifinancas.ui.components.CardFinanceItem
import com.br.ifinancas.ui.components.ViewSobreposta
import com.br.ifinancas.ui.theme.BACKGROUNDCARDSOBREPOSTO

@Composable
fun BalanceCards(
    totalRevenues: Float,
    nav: NavHostController,
    balanceIsVisible: Boolean,
    totalExpenses: Float,
    totalReservations: Float,
) {
    ViewSobreposta {
        Card(
            modifier = Modifier.fillMaxWidth(0.95f),
            shape = RoundedCornerShape(12.dp),
            elevation = 10.dp,
            backgroundColor = BACKGROUNDCARDSOBREPOSTO
        ) {
            Box(modifier = Modifier.padding(10.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    CardFinanceItem(
                        iconeImage = R.drawable.receita,
                        text = stringResource(R.string.receitas),
                        valor = totalRevenues,
                        tag = Tags.REVENUE.tag,
                        nav = nav,
                        balanceIsVisible
                    )
                    CardFinanceItem(
                        iconeImage = R.drawable.despesa,
                        text = stringResource(R.string.despesas),
                        valor = totalExpenses,
                        tag = Tags.EXPENSE.tag,
                        nav = nav,
                        balanceIsVisible
                    )
                    CardFinanceItem(
                        iconeImage = R.drawable.reserva,
                        text = stringResource(R.string.reservas),
                        valor = totalReservations,
                        tag = Tags.RESERVATION.tag,
                        nav = nav,
                        balanceIsVisible,
                    )
                }
            }
        }
    }
}