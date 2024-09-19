package com.br.ifinancas.ui.screens.home.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.br.ifinancas.ui.screens.home.HomeUiState
import com.br.ifinancas.ui.screens.home.HomeViewModel
import com.br.ifinancas.utils.AppUtils.Companion.toDefaultCurrency
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Edit

@Composable
fun FiiComponent(
    homeViewModel: HomeViewModel,
    homeUiState: State<HomeUiState>
) {

    Column(modifier = Modifier.padding(start = 20.dp)) {
        Spacer(modifier = Modifier.height(20.dp))
        homeUiState.value.fiisList.forEach {
            Column {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "${it.fiisName.nome} - ${it.fii.cotas} cotas, ${toDefaultCurrency(it.fii.proventos)} proventos",
                        fontSize = 14.sp
                    )
                    Icon(
                        imageVector = FontAwesomeIcons.Solid.Edit,
                        contentDescription = null,
                        tint = Color.Black,
                        modifier = Modifier
                            .size(15.dp)
                            .clickable {
                                homeViewModel.selectFiiForEdit(it)

                            }
                    )
                }

                val fiiSelected = homeUiState.value.fiiSelectedForEdit
                if (fiiSelected != null && it.fii.id == fiiSelected.fii.id)
                    InputsRow(
                        homeViewModel,
                        firstInputPlaceholder = "Cotas",
                        firstInputValue = homeUiState.value.fiiCotas,
                        onChangeFirstInput = { homeViewModel.onChangeCotas(it) },
                        secondInputPlaceholder = "Proventos",
                        secondInputValue = homeUiState.value.fiiProventos,
                        onChangeSecondInput = { homeViewModel.onChangeProventos(it) },
                        onClickButton = { homeViewModel.updateFii() }
                    )

            }
        }


    }
}
