package com.br.ifinancas.db.models.TransactionModel

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.br.ifinancas.data.enums.Category
import java.util.Date

@Entity(tableName = "transaction_table")
data class Transaction(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val amount: Float,
    val category: Category,
    val createdAt: Date,
    val description: String,
    val tag: String
)
