package com.br.ifinancas.services.impl

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import com.br.ifinancas.R
import com.br.ifinancas.services.DateTimeService
import com.br.ifinancas.utils.AppUtils.Companion.AppTag
import dagger.hilt.android.qualifiers.ApplicationContext
import java.text.SimpleDateFormat
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date
import java.util.Locale
import javax.inject.Inject


class DateTimeServiceImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : DateTimeService {
    private val months = listOf(
        context.getString(R.string.janeiro),
        context.getString(R.string.fevereiro),
        context.getString(R.string.mar_o),
        context.getString(R.string.abril),
        context.getString(R.string.maio),
        context.getString(R.string.junho),
        context.getString(R.string.julho),
        context.getString(R.string.agosto),
        context.getString(R.string.setembro),
        context.getString(R.string.outubro),
        context.getString(R.string.novembro),
        context.getString(R.string.dezembro)
    )
    private val monthsThatCanBeSelected = mapOf(
        "CURRENT" to "current",
        "PAST_MONTH" to "pastMonth",
        "THREE_MONTHS_AGO" to "threeMonthsAgo",
        "FOUR_MONTHS_AGO" to "fourMonthsAgo",
        "FIVE_MONTHS_AGO" to "fiveMonthsAgo"
    )


    override fun getMonths(): List<String> {
        return months
    }

    private fun getMonth(monthInNumber: Int): String? {
        return when (monthInNumber) {
            -2 -> context.getString(R.string.outubro)
            -1 -> context.getString(R.string.novembro)
            0 -> context.getString(R.string.dezembro)
            1 -> context.getString(R.string.janeiro)
            2 -> context.getString(R.string.fevereiro)
            3 -> context.getString(R.string.mar_o)
            4 -> context.getString(R.string.abril)
            5 -> context.getString(R.string.maio)
            6 -> context.getString(R.string.junho)
            7 -> context.getString(R.string.julho)
            8 -> context.getString(R.string.agosto)
            9 -> context.getString(R.string.setembro)
            10 -> context.getString(R.string.outubro)
            11 -> context.getString(R.string.novembro)
            12 -> context.getString(R.string.dezembro)
            else -> null
        }
    }

    override fun getPastFourMonths(): List<String?> {
        val currentDate = Calendar.getInstance()
        val currentMonth = currentDate.get(Calendar.MONTH) + 1
        val lastMonth = currentDate.get(Calendar.MONTH)
        val threeMonthsAgo = currentDate.get(Calendar.MONTH) - 1
        val fourMonthsAgo = currentDate.get(Calendar.MONTH) - 2

        return listOf(
            getMonth(fourMonthsAgo),
            getMonth(threeMonthsAgo),
            getMonth(lastMonth),
            getMonth(currentMonth)
        )
    }

    override fun convertMillisecondsToMonthAndYear(milliseconds: Long): String {
        val date = Date(milliseconds)
        val formatter = SimpleDateFormat("MM/yyyy", Locale("pt", "BR"))
        return formatter.format(date)
    }

    override fun getYesterday(): Date {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DATE, -1)
        return calendar.time
    }

    @SuppressLint("NewApi")
    override fun getDateFormatted(date: Date): String {
        // Converting java.util.Date to java.time.LocalDate
        val localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        return localDate.format(formatter)
    }

    override fun getMonthAndYear(data: Date): String {
        val mes = (data.month + 1).toString().padStart(2, '0')
        val ano = data.year + 1900 // Year is stored as the number of years since 1900

        return "$mes/$ano"
    }


    override fun getMonthAndYear(monthSelected: String): String {
        val date = Calendar.getInstance()
        var year = date.get(Calendar.YEAR)
        val month: Int = when (monthSelected) {
            monthsThatCanBeSelected["CURRENT"] -> date.get(Calendar.MONTH) + 1
            monthsThatCanBeSelected["PAST_MONTH"] -> {
                if (date.get(Calendar.MONTH) == 0) {
                    year -= 1
                    12
                } else {
                    date.get(Calendar.MONTH)
                }
            }

            monthsThatCanBeSelected["THREE_MONTHS_AGO"] -> {
                if (date.get(Calendar.MONTH) == 0) {
                    year -= 1
                    11
                } else {
                    date.get(Calendar.MONTH) - 1
                }
            }

            else -> {
                if (date.get(Calendar.MONTH) == 0) {
                    year -= 1
                    10
                } else {
                    date.get(Calendar.MONTH) - 2
                }
            }
        }
        return "${month.toString().padStart(2, '0')}/$year"
    }

    override fun getMonthAndYearFromGivenMonthWritten(monthName: String, year: Int): String {
        val monthIndex = months.indexOfFirst { it.equals(monthName, ignoreCase = true) }
        if (monthIndex == -1) {
            Log.d(AppTag, "mês: $monthName")
            throw IllegalArgumentException("Nome do mês inválido.")
        }
        val month = (monthIndex + 1).toString().padStart(2, '0')
        return "$month/$year"
    }

    override fun getMonthName(date: Date): String {
        val calendar = Calendar.getInstance()
        calendar.time = date
        val numberOfMonth = calendar.get(Calendar.MONTH)
        return months[numberOfMonth]
    }

    override fun increaseMonth(date: Date): Date {
        val calendar = Calendar.getInstance()
        calendar.time = date
        calendar.add(Calendar.MONTH, 1)
        return calendar.time
    }

    override fun decreaseMonth(date: Date): Date {
        val calendar = Calendar.getInstance()
        calendar.time = date
        calendar.add(Calendar.MONTH, -1)
        return calendar.time
    }

}