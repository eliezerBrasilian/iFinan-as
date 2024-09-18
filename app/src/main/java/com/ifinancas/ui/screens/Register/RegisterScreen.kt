package com.ifinancas.ui.screens.Register

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ifinancas.R
import com.ifinancas.db.models.TransactionModel.TransactionViewModel
import com.ifinancas.navigation.CustomTopBar
import com.ifinancas.navigation.NavigationBarColor
import com.ifinancas.ui.components.RegisterScreenOverlayView
import com.ifinancas.ui.components.RegisterValueInput
import com.ifinancas.utils.AppUtils.Companion.getBackgroundColor
import com.ifinancas.utils.AppUtils.Companion.getTitleText

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RegisterScreen(
    nav: NavHostController = rememberNavController(),
    tag: String? = stringResource(R.string.receita),
    pv: PaddingValues = PaddingValues(0.dp),
    transactionViewModel: TransactionViewModel = hiltViewModel()
) {
    val uiState = transactionViewModel.uiState.collectAsState()

    val focusRequester by remember { mutableStateOf(FocusRequester()) }
    val context = LocalContext.current

    val backgroundSelected by remember {
        mutableStateOf(getBackgroundColor(tag.toString()))
    }

    NavigationBarColor(statusBarColor = backgroundSelected, useDarkIcons = false)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(getBackgroundColor(tag.toString()))
            .padding(pv)
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            CustomTopBar(
                color = Color.White,
                text = getTitleText(tag.toString(), context),
                nav = nav
            )
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                RegisterValueInput(
                    uiState.value.valueInput,
                    transactionViewModel.onValueChange,
                    tag,
                    uiState.value.isFocused,
                    transactionViewModel.onFocusChange,
                    focusRequester
                )
            }
        }
        Column {
            Spacer(modifier = Modifier.height(10.dp))
            RegisterScreenOverlayView(
                uiState.value.daySelected,
                transactionViewModel.handleSelectDayChange,
                uiState.value.categorySelected,
                transactionViewModel.handleCategoryChange,
                uiState.value.categoryExpanded,
                transactionViewModel.toggleCategoryExpanded,
                tag,
                uiState.value.buttonIsLoading,
                uiState.value.descriptionInput,
                transactionViewModel.onDescriptionChange,
                {
                    transactionViewModel.handleSendRegister(
                        context,
                        tag
                    )
                },
                uiState.value.savedSuccessfully
            )

        }
    }
}


