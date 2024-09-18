package com.ifinancas.ui.screens

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ifinancas.data.customremembers.rememberCustomModalBottomSheetState
import com.ifinancas.data.enums.Tags
import com.ifinancas.db.models.TransactionModel.Transaction
import com.ifinancas.db.models.TransactionModel.TransactionViewModel
import com.ifinancas.navigation.CustomTopBar
import com.ifinancas.navigation.NavigationBarColor
import com.ifinancas.ui.components.FinanceSelectRow
import com.ifinancas.ui.components.FinancialBalanceSelectedMenu
import com.ifinancas.ui.components.FinancialBalanceSelectedTop
import com.ifinancas.ui.components.PopUpDeleteRegister
import com.ifinancas.ui.components.TransactionHistoryOverlayView
import com.ifinancas.utils.AppUtils
import com.ifinancas.utils.AppUtils.Companion.AppTag

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FinancialBalanceSelected(
    pv: PaddingValues = PaddingValues(0.dp),
    tag: String? = "receita",
    nav: NavHostController,
    transactionViewModel: TransactionViewModel = hiltViewModel()
) {

    var tagSelected by remember {
        mutableStateOf(tag.toString())
    }

    Log.d(AppTag, "Tag seleced: $tagSelected")

    var expanded by remember {
        mutableStateOf(false)
    }

    var backgroundSelected by remember {
        mutableStateOf(AppUtils.getBackgroundColor(tagSelected))
    }

    NavigationBarColor(statusBarColor = backgroundSelected, useDarkIcons = false)
    val onChangeTag: (tag: String) -> Unit = {
        tagSelected = it
        backgroundSelected = AppUtils.getBackgroundColor(it)
    }


    var popUpDeleteRegisterIsVisible by remember {
        mutableStateOf(false)
    }

    var id by remember {
        mutableIntStateOf(-1)
    }

    var currentMonthYear by remember { mutableStateOf(transactionViewModel.getCurrentMonthYear()) }

    var totalAmount by remember { mutableFloatStateOf(0f) }

    var transactions by remember { mutableStateOf<List<Transaction>>(emptyList()) }
    // Carrega o total de todas as transações para o mês especificado
    LaunchedEffect(currentMonthYear, tagSelected) {
        var currentTag: Tags;

        when (tagSelected) {
            Tags.EXPENSE.tag -> {
                currentTag = Tags.EXPENSE
            }

            Tags.REVENUE.tag -> {
                currentTag = Tags.REVENUE
            }

            else -> {
                currentTag = Tags.RESERVATION
            }
        }

        transactionViewModel.getFinanceSelectedTotalByTagAndMonth(currentTag, currentMonthYear) {
            totalAmount = it
        }
        transactionViewModel.getTransactionsByTagAndMonth(tagSelected, currentMonthYear)
            .collect { transactionsList ->
                transactions = transactionsList
            }
    }

    val handleDeleteRegister: (registerId: Int) -> Unit = {
        id = it
        popUpDeleteRegisterIsVisible = !popUpDeleteRegisterIsVisible
    }

    val context = LocalContext.current
    val sheetState = rememberCustomModalBottomSheetState(
        tag = tag.toString()
    )

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
                currentDate = currentMonthYear,
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
            .background(backgroundSelected)
    ) {
        Column {
            Box(modifier = Modifier.padding(10.dp)) {
                CustomTopBar(
                    color = Color.White,
                    nav = nav,
                   )
            }

            FinanceSelectRow(expanded = expanded, tagSelected) {
                expanded = !expanded
            }
            if (expanded) {
                FinancialBalanceSelectedMenu(tagSelected, onChangeTag)
            }

            Column(modifier = Modifier.padding(10.dp)) {
                FinancialBalanceSelectedTop(
                    totalAmount,
                    tagSelected
                )
            }
        }
    }
}

