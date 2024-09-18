package com.ifinancas.navigation

//noinspection UsingMaterialAndMaterial3Libraries
import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.ifinancas.db.models.TransactionModel.TransactionViewModel
import com.ifinancas.ui.screens.FinancialBalanceSelected
import com.ifinancas.ui.screens.Home.Home
import com.ifinancas.ui.screens.Home.HomeViewModel
import com.ifinancas.ui.screens.Register.RegisterScreen
import com.ifinancas.ui.screens.transactionsHistory.TransactionsHistory


@SuppressLint(
    "NewApi", "UnusedMaterialScaffoldPaddingParameter", "ComposableDestinationInComposeScope"
)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppGraph(
    navController: NavHostController,
    homeViewModel: HomeViewModel,
    transactionViewModel: TransactionViewModel,
) {
    val context = LocalContext.current

    Scaffold(bottomBar = { CustomBottomBar(navController) }) { pv ->
        NavHost(navController = navController,
            route = "navHost",
            startDestination = "appNavigation",
            modifier = Modifier
                .padding(0.dp)
                .background(Color.White),
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None },
            builder = {
                navigation(
                    startDestination = BottomBarScreen.Home(context).route,
                    route = "appNavigation"
                ) {
                    composable(BottomBarScreen.Home(context).route) {
                        Home(
                            navController,
                            pv,
                            homeViewModel,
                            transactionViewModel
                        )
                    }
                    composable(NavigationScreens.PROFILE) {
                        //todo SAIR
                    }
                    composable(BottomBarScreen.Historico(context).route) {
                        TransactionsHistory(
                            pv
                        )
                    }
                    composable(
                        NavigationScreens.FINANCIAL_BALANCE_SELECTED + "/{tag}",
                        arguments = listOf(navArgument(name = "tag") { type = NavType.StringType })
                    ) { backStackEntry ->
                        val tag = backStackEntry.arguments?.getString("tag")

                        FinancialBalanceSelected(
                            pv,
                            tag,
                            navController,
                        )
                    }

                    composable(
                        NavigationScreens.REGISTER + "/{tag}",
                        enterTransition = {
                            return@composable slideIntoContainer(
                                AnimatedContentTransitionScope.SlideDirection.Up, tween(400)
                            )
                        },
                        arguments = listOf(navArgument(name = "tag") { type = NavType.StringType })
                    ) { backStackEntry ->
                        val tag = backStackEntry.arguments?.getString("tag")
                        RegisterScreen(
                            navController,
                            tag,
                            pv,
                            transactionViewModel
                        )
                    }
                }
            })
    }
}

