package com.ifinancas.ui.screens.Home.widgets

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.frajola.BrazilianCurrencyVisualTransformation
import com.ifinancas.ui.screens.Home.HomeUiState
import com.ifinancas.ui.screens.Home.HomeViewModel
import com.ifinancas.utils.AppUtils.Companion.toDefaultCurrency
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Edit

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PatrimonioComponent(
    homeViewModel: HomeViewModel,
    homeUiState: State<HomeUiState>,
    balance: Float,
) {

    val context = LocalContext.current
    Column(modifier = Modifier.padding(start = 20.dp)) {
        Spacer(modifier = Modifier.height(20.dp))
        LabelAndButtonAdd(
            homeUiState,
            label = "Patrimônio Total",
            onClick = { homeViewModel.togglePatrimonioInputVisibility() })
        if (homeUiState.value.inputPatrimonioNameIsVisible) {
            Spacer(Modifier.height(20.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = homeUiState.value.patrimonioName,
                    placeholder = { Text(text = "Nome...", fontSize = 13.sp) },
                    onValueChange = { homeViewModel.onChangePatrimonioName(it) },
                    modifier = Modifier.width(110.dp)
                )
                Button(onClick = { homeViewModel.insertPatrimonio(context) }) {
                    Text(text = "Salvar")
                }
            }
        }

        homeUiState.value.patrimonioList.forEach {
            Column {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "${it.patrimonioNames.nome} - ${toDefaultCurrency(it.patrimonioModel.total)}",
                        fontSize = 14.sp
                    )
                    Icon(
                        imageVector = FontAwesomeIcons.Solid.Edit,
                        contentDescription = null,
                        tint = Color.Black,
                        modifier = Modifier
                            .size(15.dp)
                            .clickable {
                                homeViewModel.selectPatrimonioForEdit(it)
                            }
                    )
                }

                val selected = homeUiState.value.patrimonioSelectedForEdit
                if (selected != null && it.patrimonioModel.id == selected.patrimonioModel.id)
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(20.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        OutlinedTextField(
                            value = homeUiState.value.patrimonioTotalInput,
                            onValueChange = { homeViewModel.onChangePatrimonioTotal(it) },
                            modifier = Modifier.fillMaxWidth(0.2f),
                            placeholder = { Text(text = "Total...", fontSize = 13.sp) },
                            visualTransformation = BrazilianCurrencyVisualTransformation()
                        )

                        Button(onClick = { homeViewModel.updatePatrimonio() }) {
                            Text(text = "Salvar")
                        }
                    }
            }
        }

        Text(
            text = "Total = ${toDefaultCurrency(homeUiState.value.patrimonioTotalCount + balance)}",
            fontSize = 14.sp
        )


    }
}