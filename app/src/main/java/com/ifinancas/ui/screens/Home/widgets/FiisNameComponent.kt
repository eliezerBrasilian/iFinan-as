package com.ifinancas.ui.screens.Home.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ifinancas.db.models.FiiNamesModel.FiisNamesModel
import com.ifinancas.ui.screens.Home.HomeUiState
import com.ifinancas.ui.screens.Home.HomeViewModel

@Composable
fun FiisNameComponent(
    homeViewModel: HomeViewModel,
    homeUiState: State<HomeUiState>,
    fiisList: List<FiisNamesModel>
) {
    Column(modifier = Modifier.padding(start = 20.dp)) {
        Text(
            text = "Meus investimentos",
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(20.dp))
        LabelAndButtonAdd(
            homeUiState,
            label = "FIIs",
            onClick = { homeViewModel.toggleAddFiiInputVisibility() })
        LazyRow(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
            items(fiisList) {
                Box(
                    modifier = Modifier
                        .background(
                            color = Color.DarkGray, shape = RoundedCornerShape(19.dp)
                        )
                        .padding(10.dp)
                ) {
                    Text(text = it.nome, fontSize = 10.sp, color = Color.White)
                }
            }
        }

        if (homeUiState.value.inputFiiNameIsVisible) {
            Spacer(Modifier.height(20.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = homeUiState.value.fiiName,
                    placeholder = { Text(text = "Nome...", fontSize = 13.sp) },
                    onValueChange = { homeViewModel.onChangeFiiName(it) },
                    modifier = Modifier.fillMaxWidth(0.3f)
                )
                Button(onClick = { homeViewModel.insertFiiName() }) {
                    Text(text = "Salvar")
                }
            }
        }
    }
}