package com.br.ifinancas.ui.screens.home.widgets

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.br.ifinancas.db.models.PatrimonioModel.PatrimonioWithName
import com.br.ifinancas.ui.customEffects.PopUpDeleteRegisterEffect
import com.br.ifinancas.ui.screens.home.HomeUiState
import com.br.ifinancas.ui.screens.home.HomeViewModel
import com.br.ifinancas.utils.AppUtils.Companion.toDefaultCurrency
import com.frajola.BrazilianCurrencyVisualTransformation
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

        PatrimonioListComponent(homeUiState, homeViewModel)

        Text(
            text = "Total = ${toDefaultCurrency(homeUiState.value.patrimonioTotalCount + balance)}",
            fontSize = 14.sp
        )


    }
}

@Composable
private fun PatrimonioListComponent(
    homeUiState: State<HomeUiState>,
    homeViewModel: HomeViewModel
) {
    homeUiState.value.patrimonioList.forEach {
        PatrimonioItemComposable(it, homeViewModel, homeUiState)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun PatrimonioItemComposable(
    it: PatrimonioWithName,
    homeViewModel: HomeViewModel,
    homeUiState: State<HomeUiState>
) {

    var popupDeleteVisible by remember {
        mutableStateOf(false)
    }

    PopUpDeleteRegisterEffect(
        popupDeleteVisible,
        title = it.patrimonioNames.nome,
        onDismissRequest = {
            popupDeleteVisible = false
        },
        delete = {
            homeViewModel.deletePatrimonio(it.patrimonioModel.id, it.patrimonioModel.total)
        })

    Column {
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.combinedClickable(onClick = {}, onLongClick = {
                popupDeleteVisible = true
            })
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
        EditPatrimonioComposable(it, homeUiState, homeViewModel)
    }
}

@Composable
private fun EditPatrimonioComposable(
    it: PatrimonioWithName,
    homeUiState: State<HomeUiState>,
    homeViewModel: HomeViewModel
) {
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