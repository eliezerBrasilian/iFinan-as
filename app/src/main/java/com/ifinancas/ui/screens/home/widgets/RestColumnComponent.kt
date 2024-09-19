package com.br.ifinancas.ui.screens.home.widgets

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.br.ifinancas.ui.screens.home.HomeUiState
import com.br.ifinancas.ui.screens.home.HomeViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RestColumnComponent(
    homeViewModel: HomeViewModel,
    homeUiState: State<HomeUiState>,
    balance: Float
) {
    val fiisList by homeViewModel.allFiisName.collectAsState(initial = emptyList())

    FiisNameComponent(homeViewModel, homeUiState, fiisList)
    FiiComponent(homeViewModel, homeUiState)

    PatrimonioComponent(homeViewModel, homeUiState, balance)
}