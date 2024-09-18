package com.ifinancas.db.models.TransactionModel

import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ifinancas.R
import com.ifinancas.data.enums.Category
import com.ifinancas.data.enums.Dia
import com.ifinancas.data.enums.Tags
import com.ifinancas.services.DateTimeService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date
import javax.inject.Inject

const val MAX_CHARACTER = 70

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class TransactionViewModel @Inject constructor(
    private val repository: TransactionRepository,
    private val dateTimeService: DateTimeService
) : ViewModel() {

    private val _uiState = MutableStateFlow(TransactionUiState())
    val uiState = _uiState.asStateFlow()

    val allTransactions: Flow<List<Transaction>> = repository.allTransactions

    fun insert(transaction: Transaction) = viewModelScope.launch {
        repository.insert(transaction)
    }

    // Obter o total de uma tag específica em um mês
    fun getTotalByTagAndMonth(tag: Tags, monthYear: String): StateFlow<Float> {
        val total = MutableStateFlow(0f)

        viewModelScope.launch {
            total.value = repository.getTotalByTagAndMonth(tag, monthYear)
        }

        return total
    }

    fun getFinanceSelectedTotalByTagAndMonth(
        tag: Tags, monthYear: String,
        callback: (total: Float) -> Unit
    ): Unit {

        viewModelScope.launch {
            val total = repository.getTotalByTagAndMonth(tag, monthYear)
            callback(total)
        }


    }

    fun getTotalByMonth(monthYear: String, callback: (total: Float) -> Unit): Unit {
        viewModelScope.launch {
            val total = repository.getTotalByMonth(monthYear)
            callback(total)
        }
    }

    fun getTransactionsByTagAndMonth(tag: String, monthYear: String): Flow<List<Transaction>> {
        return repository.getTransactionsByTagAndMonth(tag, monthYear)
    }


    val onValueChange: (value: String) -> Unit = { v ->
        val onlyDigits = v.filter { char -> char.isDigit() }
        _uiState.update { it.copy(valueInput = onlyDigits) }
    }

    val onFocusChange: (focused: Boolean) -> Unit = { v ->
        _uiState.update { it.copy(isFocused = v) }
    }

    val onDescriptionChange: (value: String) -> Unit = { v ->
        if (v.length <= MAX_CHARACTER) {
            _uiState.update { it.copy(descriptionInput = v) }
        }
    }

    val toggleCategoryExpanded = {
        _uiState.update { it.copy(categoryExpanded = !it.categoryExpanded) }
    }

    val handleCategoryChange: (category: Category) -> Unit = { v ->
        _uiState.update { it.copy(categoryExpanded = false, categorySelected = v) }
    }

    val handleSelectDayChange: (day: Dia) -> Unit = { day ->
        _uiState.update { it.copy(daySelected = day) }

        if (day == Dia.TODAY) {
            _uiState.update { it.copy(localDateTime = Date()) }
        }
        if (day == Dia.YESTERDAY) {
            _uiState.update { it.copy(localDateTime = dateTimeService.getYesterday()) }
        }
    }


    val successOnRegister: (context: Context) -> Unit = {
        println("Sucesso")
        Toast.makeText(it, R.string.registrado_com_sucesso, Toast.LENGTH_LONG).show()
        _uiState.update { it.copy(savedSuccessfully = true) }
        clearInputs()
    }

    val clearInputs = {
        _uiState.update {
            it.copy(
                valueInput = "",
                descriptionInput = "",
                daySelected = Dia.TODAY,
                localDateTime = Date(),
                categorySelected = Category.OUTROS
            )
        }
    }

    fun handleSendRegister(
        context: Context,
        tag: String?
    ) {
        _uiState.update { it.copy(buttonIsLoading = true) }


        var v = 0.0f
        if (_uiState.value.valueInput.isNotEmpty()) {
            v = _uiState.value.valueInput.toFloat() / 100
        }

        //val registerData = registerFormatted(tag, v, uid)

        val value = _uiState.value

        viewModelScope.launch {
            repository.insert(
                Transaction(
                    amount = v,
                    createdAt = Date(),
                    tag = tag.toString(),
                    description = value.descriptionInput.trim(),
                    category = value.categorySelected
                )
            )
        }
        _uiState.update { it.copy(buttonIsLoading = false) }
        successOnRegister(context)
    }


    private val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM")

    // Obtém o mês e ano atual no formato YYYY-MM
    fun getCurrentMonthYear(): String {
        return LocalDate.now().format(dateFormatter)
    }

    // Incrementa o mês
    fun incrementMonth(currentMonthYear: String): String {
        val currentDate =
            LocalDate.parse("$currentMonthYear-01", DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        val newDate = currentDate.plusMonths(1)
        return newDate.format(dateFormatter)
    }

    // Decrementa o mês
    fun decrementMonth(currentMonthYear: String): String {
        val currentDate =
            LocalDate.parse("$currentMonthYear-01", DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        val newDate = currentDate.minusMonths(1)
        return newDate.format(dateFormatter)
    }

    fun deleteTransactionById(id: Int, callback: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                repository.deleteById(id)
                callback(true)
            } catch (e: Exception) {
                callback(false)
            }
        }
    }

    // StateFlow que vai manter o saldo do mês
    private val _balance = MutableStateFlow(0f)
    val balance: StateFlow<Float> = _balance
    fun calculateMonthlyBalance(monthYear: String) {
        viewModelScope.launch {
            val totalRevenue = repository.getTotalRevenueForMonth(monthYear)
            val totalExpense = repository.getTotalExpenseForMonth(monthYear)
            val totalReservation = repository.getTotalReservationForMonth(monthYear)

            val calculatedBalance = totalRevenue - (totalExpense + totalReservation)
            _balance.value = calculatedBalance
        }

    }
}
