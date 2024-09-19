package com.br.ifinancas.db.models.TransactionModel

import com.br.ifinancas.data.enums.Category
import com.br.ifinancas.data.enums.Dia
import java.util.Date

data class TransactionUiState(
    val valueInput: String = "",
    val daySelected: Dia = Dia.TODAY,
    val categorySelected: Category = Category.OUTROS,
    val categoryExpanded: Boolean = false,
    val descriptionInput: String = "",
    val buttonIsLoading: Boolean = false,
    val localDateTime: Date = Date(),
    val savedSuccessfully: Boolean = false,
    val isFocused: Boolean = false,
    val total: Float = 0f,
    val transactionList: List<Transaction> = emptyList(),
    val totalSelected: Float = 0f,
    val reload: Boolean = false
)