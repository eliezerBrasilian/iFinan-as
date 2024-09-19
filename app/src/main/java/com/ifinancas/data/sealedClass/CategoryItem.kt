package com.br.ifinancas.data.sealedClass

import androidx.compose.ui.graphics.Color
import com.br.ifinancas.data.enums.Category
import com.br.ifinancas.ui.theme.APOSTACOLOR
import com.br.ifinancas.ui.theme.ASSINATURACOLOR
import com.br.ifinancas.ui.theme.CASACOLOR
import com.br.ifinancas.ui.theme.INVESTIMENTOCOLOR
import com.br.ifinancas.ui.theme.LANCHE_FASTFOODCOLOR
import com.br.ifinancas.ui.theme.LAZERCOLOR
import com.br.ifinancas.ui.theme.OUTROSCOLOR
import com.br.ifinancas.ui.theme.SAUDECOLOR
import com.br.ifinancas.ui.theme.TRABALHOCOLOR

sealed class CategoryItem(val category: Category, val color: Color) {
    data object Apostas : CategoryItem(category = Category.APOSTAS, color = APOSTACOLOR)
    data object Assinatura : CategoryItem(category = Category.ASSINATURA, color = ASSINATURACOLOR)
    data object CASA : CategoryItem(category = Category.CASA, color = CASACOLOR)
    data object INVESTIMENTO :
        CategoryItem(category = Category.INVESTIMENTO, color = INVESTIMENTOCOLOR)

    data object LANCHE :
        CategoryItem(category = Category.LANCHE_FASTFOOD, color = LANCHE_FASTFOODCOLOR)

    data object LAZER : CategoryItem(category = Category.LAZER, color = LAZERCOLOR)
    data object OUTROS : CategoryItem(category = Category.OUTROS, color = OUTROSCOLOR)
    data object Saude : CategoryItem(category = Category.SAUDE, color = SAUDECOLOR)
    data object TRABALHO : CategoryItem(category = Category.TRABALHO, color = TRABALHOCOLOR)
}

val categories = listOf(
    CategoryItem.Apostas,
    CategoryItem.Assinatura,
    CategoryItem.CASA,
    CategoryItem.INVESTIMENTO,
    CategoryItem.LANCHE,
    CategoryItem.LAZER,
    CategoryItem.OUTROS,
    CategoryItem.Saude,
    CategoryItem.TRABALHO
)
