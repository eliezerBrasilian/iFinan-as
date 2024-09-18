package com.ifinancas.services

import android.annotation.SuppressLint
import java.util.Calendar
import java.util.Date

interface DateTimeService {

    fun getMonths(): List<String>

    fun getPastFourMonths(): List<String?>

    fun convertMillisecondsToMonthAndYear(milliseconds: Long): String

    fun getYesterday(): Date

    @SuppressLint("NewApi")
    fun getDateFormatted(date: Date = Date()): String

    fun getMonthAndYear(data: Date = Date()): String

    fun getMonthAndYear(monthSelected: String): String

    fun getMonthAndYearFromGivenMonthWritten(
        monthName: String = "Janeiro",
        year: Int = Calendar.getInstance().get(Calendar.YEAR)
    ): String

    fun getMonthName(date: Date = Date()): String

    fun increaseMonth(date: Date): Date

    fun decreaseMonth(date: Date): Date

}
