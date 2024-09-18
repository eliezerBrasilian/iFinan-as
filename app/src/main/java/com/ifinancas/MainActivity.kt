package com.ifinancas

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.adapty.Adapty
import com.adapty.models.AdaptyConfig
import com.ifinancas.data.gitignore.adaptyId
import com.ifinancas.db.models.TransactionModel.TransactionViewModel
import com.ifinancas.navigation.AppGraph
import com.ifinancas.navigation.NavigationBarColor
import com.ifinancas.ui.components.PopUpAddRegisterDialog
import com.ifinancas.ui.screens.Home.HomeViewModel
import com.ifinancas.ui.theme.IFinançasTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val homeViewModel: HomeViewModel by viewModels()


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        Adapty.activate(
            applicationContext,
            AdaptyConfig.Builder(adaptyId)
                .build()
        )

        setContent {
            val transactionViewModel: TransactionViewModel = hiltViewModel()

            IFinançasTheme {
                val navController: NavHostController = rememberNavController()
                val homeUiState = homeViewModel.uiState.collectAsState()

                NavigationBarColor(
                    statusBarColor = Color(0xff38504F),
                    useDarkIcons = false
                )

                AppGraph(
                    navController = navController,
                    homeViewModel = homeViewModel,
                    transactionViewModel = transactionViewModel,
                )

                if (homeUiState.value.popupAddRegisterIsVisible) PopUpAddRegisterDialog(
                    onDismissRequest = {
                        homeViewModel.togglePopupRegisterVisibility()
                    }, navController
                )
            }
        }
    }

}



