package com.ifinancas.data.enums

import android.content.Context
import com.ifinancas.R
import com.ifinancas.navigation.NavigationScreens

sealed class MenuItem(val icon: Int, val title: String, val route: String = "") {
    data class Perfil(val context: Context) : MenuItem(
        R.drawable.perfil, context.getString(R.string.perfil),
        NavigationScreens.PROFILE
    )

    data class Avaliar(val context: Context) : MenuItem(
        R.drawable.estrela,
        context.getString(R.string.avaliar)
    )

    data class Compartilhar(val context: Context) :
        MenuItem(R.drawable.compartilhar_amigos, context.getString(R.string.compartilhar))
}