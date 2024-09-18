package com.ifinancas.data.enums

import android.content.Context
import com.ifinancas.R

enum class Category(val stringResId: Int) {
    APOSTAS(R.string.apostas),
    ASSINATURA(R.string.assinatura_plano),
    CASA(R.string.casa),
    LANCHE_FASTFOOD(R.string.lanche_fastfood),
    LAZER(R.string.lazer),
    TRABALHO(R.string.trabalho),
    INVESTIMENTO(R.string.investimento),
    OUTROS(R.string.outros),
    SAUDE(R.string.sa_de);

    fun getValue(context: Context): String {
        return context.getString(stringResId)
    }
}
