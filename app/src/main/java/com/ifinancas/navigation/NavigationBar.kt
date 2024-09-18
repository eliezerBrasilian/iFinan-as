package com.ifinancas.navigation

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun NavigationBarColor(
    statusBarColor: Color = Color.White,
    navigationBarColor: Color = Color.White,
    useDarkIcons: Boolean = true
) {
    val systemUiController = rememberSystemUiController()

    val dispatcher = LocalOnBackPressedDispatcherOwner.current

    LaunchedEffect(systemUiController, dispatcher, statusBarColor, navigationBarColor) {

        systemUiController.setStatusBarColor(
            color = statusBarColor,
            darkIcons = useDarkIcons,
        )
        systemUiController.setNavigationBarColor(
            color = navigationBarColor,
            darkIcons = useDarkIcons
        )

        // Dispose the effect when the composable leaves the composition
//        onDispose {
//            /* systemUiController.setStatusBarColor(
//                 color = androidx.compose.ui.graphics.Color.Transparent,
//                 darkIcons = useDarkIcons,
//             )
//             systemUiController.setNavigationBarColor(
//                 color = androidx.compose.ui.graphics.Color.Transparent,
//                 darkIcons = useDarkIcons
//             )*/
//        }
    }
}
