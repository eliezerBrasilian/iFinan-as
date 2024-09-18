package com.ifinancas.ui.screens.Home

import android.content.Intent
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
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ifinancas.data.enums.MenuItem
import com.ifinancas.data.enums.Tags
import com.ifinancas.data.gitignore.appGooglePlayUri
import com.ifinancas.db.models.TransactionModel.TransactionViewModel
import com.ifinancas.navigation.NavigationBarColor
import com.ifinancas.ui.customEffects.PermissionRequestEffect
import com.ifinancas.ui.screens.Home.widgets.BalanceCards
import com.ifinancas.ui.screens.Home.widgets.FiiComponent
import com.ifinancas.ui.screens.Home.widgets.FiisNameComponent
import com.ifinancas.ui.screens.Home.widgets.HomeBlueTop
import com.ifinancas.ui.screens.Home.widgets.HomePopUpMenu
import com.ifinancas.ui.screens.Home.widgets.MonthListPopUpDialog
import com.ifinancas.ui.screens.Home.widgets.PatrimonioComponent
import com.ifinancas.ui.theme.MAINPURPLE
import com.ifinancas.utils.AppUtils

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

    val uriHandler = LocalUriHandler.current
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

    val onChangeMenuItem: (menuItem: MenuItem) -> Unit = {
        if (it.route.isNotEmpty()) {
            nav.navigate(it.route)
        } else {
            if (it.title == MenuItem.Avaliar(context).title) {
                uriHandler.openUri(appGooglePlayUri)
            } else if (it.title == MenuItem.Compartilhar(context).title) {
                val customSharePhraseContent =
                    "Baixe já o iFinanças"
                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, customSharePhraseContent)
                    type = "text/plain"
                }
                val shareIntent = Intent.createChooser(sendIntent, null)
                context.startActivity(shareIntent)
            }
        }
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
                nav,
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
        if (homeUiState.value.menuListVisible) HomePopUpMenu(
            onChangeMenuItem = onChangeMenuItem,
        )
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

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun RestColumnComponent(
    homeViewModel: HomeViewModel,
    homeUiState: State<HomeUiState>,
    balance: Float
) {
    val fiisList by homeViewModel.allFiisName.collectAsState(initial = emptyList())

    FiisNameComponent(homeViewModel, homeUiState, fiisList)
    FiiComponent(homeViewModel, homeUiState)

    PatrimonioComponent(homeViewModel, homeUiState, balance)
}





