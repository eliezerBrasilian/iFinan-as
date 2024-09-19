package com.br.ifinancas.ui.screens.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.br.ifinancas.data.enums.Tags
import com.br.ifinancas.db.models.TransactionModel.TransactionViewModel
import com.br.ifinancas.navigation.NavigationBarColor
import com.br.ifinancas.ui.customEffects.PermissionRequestEffect
import com.br.ifinancas.ui.screens.home.widgets.BalanceCards
import com.br.ifinancas.ui.screens.home.widgets.HomeBlueTop
import com.br.ifinancas.ui.screens.home.widgets.MonthListPopUpDialog
import com.br.ifinancas.ui.screens.home.widgets.RestColumnComponent
import com.br.ifinancas.ui.theme.MAINPURPLE
import com.br.ifinancas.utils.AppUtils

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun Home(
    nav: NavHostController = rememberNavController(),
    pv: PaddingValues,
    homeViewModel: HomeViewModel = hiltViewModel(),
    transactionViewModel: TransactionViewModel
) {

    val homeUiState = homeViewModel.uiState.collectAsState()

    val currentMonthYear by remember { mutableStateOf(transactionViewModel.getCurrentMonthYear()) }

    val totalRevenue by transactionViewModel.getTotalByTagAndMonth(Tags.REVENUE, currentMonthYear)
        .collectAsState()
    val totalExpense by transactionViewModel.getTotalByTagAndMonth(Tags.EXPENSE, currentMonthYear)
        .collectAsState()
    val totalReservation by transactionViewModel.getTotalByTagAndMonth(
        Tags.RESERVATION, currentMonthYear
    ).collectAsState()

    val context = LocalContext.current

    // Coletando o StateFlow de balance
    val balance by transactionViewModel.balance.collectAsState()

    // Quando a tela for exibida, calcular o saldo para o mês fornecido
    LaunchedEffect(currentMonthYear) {
        transactionViewModel.calculateMonthlyBalance(currentMonthYear)
    }

    LaunchedEffect(
        homeUiState.value.monthYear,
        homeUiState.value.reloadScreen
    ) {
        homeViewModel.loadFiis()
        homeViewModel.loadPatrimonios()
    }

    PermissionRequestEffect()
    NavigationBarColor(
        statusBarColor = MAINPURPLE, useDarkIcons = false
    )

    if (AppUtils.isTabletInLandscape(context)) {
        HomeTablet(nav)
    } else Surface(
        modifier = Modifier
            .padding(pv)
            .fillMaxSize()
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            HomeBlueTop(
                { homeViewModel.toggleMonthListVisibility() },
                { homeViewModel.toggleMenuListVisibility() },
                homeUiState.value.monthListVisible,
                homeUiState.value.menuListVisible,
                homeUiState.value.monthSelected,
                homeUiState.value.balanceIsVisible,
                { homeViewModel.toggleBalanceVisibility() },
                balance
            )
            BalanceCards(
                totalRevenue,
                nav,
                homeUiState.value.balanceIsVisible,
                totalExpense,
                totalReservation
            )
            Spacer(modifier = Modifier.height(10.dp))
            RestColumnComponent(homeViewModel, homeUiState, balance)
        }

        if (homeUiState.value.monthListVisible) {
            MonthListPopUpDialog(
                { homeViewModel.toggleMonthListVisibility() },
                homeUiState.value.monthSelected,
                { month ->
//                        homeViewModel.changeMonth(
//                            month,
//                            financialOperationsViewModel
//                        )
                },
                homeUiState.value.monthListVisible//remover
            )
        }
    }
}





