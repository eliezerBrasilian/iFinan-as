package com.ifinancas.utils

import android.content.Context
import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.ui.graphics.Color
import com.ifinancas.R
import com.ifinancas.data.enums.Category
import com.ifinancas.ui.theme.BACKGROUNDGREEN
import com.ifinancas.ui.theme.BACKGROUNDRED
import com.ifinancas.ui.theme.MAINPURPLE
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class AppUtils {
    companion object {
        val AppTag = "App"

        fun showToast(context: Context, title: String) {
            Toast.makeText(context, title, Toast.LENGTH_LONG).show()
        }

        fun toBrazilianCurrency(valor: Double): String {
            val formatoMoeda = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
            return formatoMoeda.format(valor)
        }


        fun toDefaultCurrency(valor: Float): String {
            val formatoMoeda = NumberFormat.getCurrencyInstance(Locale.getDefault())

            return formatoMoeda.format(valor)
        }

        // Função modificada para gerar o mês e ano com base no incremento de meses
        fun getMesAno(monthOffset: Int): String {
            // Obter a data atual
            val calendar = Calendar.getInstance()

            // Adicionar o incremento de meses
            calendar.add(Calendar.MONTH, monthOffset)

            // Definir o formato desejado: "MM-yyyy"
            val dateFormat = SimpleDateFormat("MM-yyyy")

            // Retornar a data formatada como string
            return dateFormat.format(calendar.time)
        }

        fun getMesAno(): String {
            // Obter a data atual
            val calendar = Calendar.getInstance()

            // Definir o formato desejado: "MM-yyyy"
            val dateFormat = SimpleDateFormat("MM-yyyy")

            // Retornar a data formatada como string
            return dateFormat.format(calendar.time)
        }

        fun isTablet(context: Context): Boolean {
            return (context.resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE
        }

        fun isLandscape(context: Context): Boolean {
            return context.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
        }

        fun isTabletInLandscape(context: Context): Boolean {
            return isTablet(context) && isLandscape(context)
        }


        fun isUserInBrazil(): Boolean {
            val locale = Locale.getDefault()
            return locale.country == "BR"
        }

        fun getBackgroundColor(tag: String): Color {
            return when (tag) {
                "receita" -> BACKGROUNDGREEN
                "despesa" -> BACKGROUNDRED
                "reserva" -> MAINPURPLE
                else -> BACKGROUNDGREEN
            }
        }

        fun getBackgroundColor(category: Category, context: Context): Color {
            return when (category.getValue(context)) {
                Category.CASA.getValue(context) -> BACKGROUNDGREEN
                Category.CASA.getValue(context) -> BACKGROUNDRED
                Category.CASA.getValue(context) -> MAINPURPLE
                else -> BACKGROUNDGREEN
            }
        }

        fun getTitleText(tag: String, context: Context): String {
            return when (tag) {
                "receita" -> context.getString(R.string.nova_receita)
                "despesa" -> context.getString(R.string.nova_despesa)
                "reserva" -> context.getString(R.string.nova_reserva)
                else -> context.getString(R.string.nova_receita)
            }
        }

        fun validEmail(emailInput: String): Boolean {
            return emailInput.contains("@gmail.com") ||
                    emailInput.contains("@hotmail.com") ||
                    emailInput.contains("@outlook.com") ||
                    emailInput.contains("@yahoo.com") ||
                    emailInput.contains("@live.com")
        }

    }

}
