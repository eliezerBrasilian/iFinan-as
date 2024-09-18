package com.ifinancas.ui.screens.transactionsHistory

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ifinancas.data.customremembers.rememberCustomModalBottomSheetState
import com.ifinancas.db.models.TransactionModel.Transaction
import com.ifinancas.db.models.TransactionModel.TransactionViewModel
import com.ifinancas.navigation.NavigationBarColor
import com.ifinancas.ui.components.PopUpDeleteRegister
import com.ifinancas.ui.components.TransactionHistoryOverlayView
import com.ifinancas.ui.components.TransactionsHistoryTop
import com.ifinancas.ui.theme.MAINPURPLE

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TransactionsHistory(
    pv: PaddingValues,
    transactionViewModel: TransactionViewModel = hiltViewModel()
) {
    NavigationBarColor(statusBarColor = MAINPURPLE, useDarkIcons = false)

    var currentMonthYear by remember { mutableStateOf(transactionViewModel.getCurrentMonthYear()) }

    var popUpDeleteRegisterIsVisible by remember {
        mutableStateOf(false)
    }

    var totalAmount by remember { mutableFloatStateOf(0f) }

    var transactions by remember { mutableStateOf<List<Transaction>>(emptyList()) }


    LaunchedEffect(currentMonthYear) {
        transactionViewModel.getTotalByMonth(currentMonthYear) {
            totalAmount = it
        }

        transactionViewModel.allTransactions
            .collect { transactionsList ->
                transactions = transactionsList
            }
    }

    var id by remember {
        mutableIntStateOf(-1)
    }

    val handleDeleteRegister: (registerId: Int) -> Unit = {
        id = it
        popUpDeleteRegisterIsVisible = !popUpDeleteRegisterIsVisible
    }

    val sheetState = rememberCustomModalBottomSheetState()

    val onDismissRequestOpoUp = {
        popUpDeleteRegisterIsVisible = !popUpDeleteRegisterIsVisible
    }

    val delete = {
        transactions = transactions.filter { it.id != id }
        transactionViewModel.deleteTransactionById(id) { result ->
            if (result) {
                transactions = transactions.filter { it.id != id }
            }
            popUpDeleteRegisterIsVisible = !popUpDeleteRegisterIsVisible
        }
    }

    if (popUpDeleteRegisterIsVisible) {
        PopUpDeleteRegister(
            onDismissRequest = onDismissRequestOpoUp,
            delete = delete,
        )
    }

    ModalBottomSheetLayout(
        scrimColor = Color.Unspecified,
        sheetContent = {
            TransactionHistoryOverlayView(
                transactions,
                currentMonthYear,
                { currentMonthYear = transactionViewModel.incrementMonth(currentMonthYear) },
                { currentMonthYear = transactionViewModel.decrementMonth(currentMonthYear) },
                handleDeleteRegister
            )
        },
        sheetState = sheetState,
        sheetShape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(pv)
            .background(MAINPURPLE)
    ) {

        Column {
            Column(modifier = Modifier.padding(10.dp)) {
                TransactionsHistoryTop(totalAmount)
            }
        }
    }
}

