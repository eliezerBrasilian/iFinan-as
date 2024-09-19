package com.br.ifinancas.navigation

import android.content.Context
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.br.ifinancas.R

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector,
    val imageIcon: Int,
    val imageIconSelected: Int
) {
    data class Home(val context: Context) : BottomBarScreen(
        route = "HOME",
        title = context.getString(R.string.inicio),
        icon = Icons.Default.Home,
        imageIcon = R.drawable.casa_barra_cinza,
        imageIconSelected = R.drawable.casa_barra
    )

    data class Historico(val context: Context) : BottomBarScreen(
        route = "HISTORICO",
        title = context.getString(R.string.hist_rico),
        icon = Icons.Default.CheckCircle,
        imageIcon = R.drawable.transacoes_cinza,
        imageIconSelected = R.drawable.transacoes_roxo
    )

}
