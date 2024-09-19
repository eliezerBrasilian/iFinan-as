package com.br.ifinancas.db.models.TransactionModel

import com.br.ifinancas.data.enums.Tags
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TransactionRepository @Inject constructor(private val transactionDao: TransactionDao) {
    val allTransactions: Flow<List<Transaction>> = transactionDao.getAllTransactions()

    suspend fun insert(transaction: Transaction) {
        transactionDao.insert(transaction)
    }

    // Somar o valor das transações por tag e mês
    suspend fun getTotalByTagAndMonth(tag: Tags, monthYear: String): Float {
        return transactionDao.getTotalByTagAndMonth(tag.tag, monthYear) ?: 0f
    }

    suspend fun getTotalByMonth(monthYear: String): Float {
        return transactionDao.getTotalByMonth(monthYear) ?: 0f
    }

    fun getTransactionsByTagAndMonth(
        tag: String,
        monthYear: String
    ): Flow<List<Transaction>> {
        return transactionDao.getTransactionsByTagAndMonth(tag, monthYear)
    }

    suspend fun deleteById(id: Int) {
        return transactionDao.deleteById(id)
    }

    suspend fun getTotalRevenueForMonth(monthYear: String): Float {
        return transactionDao.getTotalRevenueForMonth(monthYear) ?: 0f
    }

    suspend fun getTotalExpenseForMonth(monthYear: String): Float {
        return transactionDao.getTotalExpenseForMonth(monthYear) ?: 0f
    }


    suspend fun getTotalReservationForMonth(monthYear: String): Float {
        return transactionDao.getTotalReservationForMonth(monthYear) ?: 0f
    }
}
