package com.br.ifinancas.db.models.TransactionModel

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {
    @Insert
    suspend fun insert(transaction: Transaction)

    @Query("SELECT * FROM transaction_table ORDER BY createdAt DESC")
    fun getAllTransactions(): Flow<List<Transaction>>

    // Somar o valor de transações com uma determinada tag e mês
    @Query("SELECT SUM(amount) FROM transaction_table WHERE tag = :tag AND strftime('%Y-%m', createdAt / 1000, 'unixepoch') = :monthYear")
    suspend fun getTotalByTagAndMonth(tag: String, monthYear: String): Float?

    // Somar o valor total de todas as transações de um determinado mês
    @Query("SELECT SUM(amount) FROM transaction_table WHERE strftime('%Y-%m', createdAt / 1000, 'unixepoch') = :monthYear")
    suspend fun getTotalByMonth(monthYear: String): Float?

    // Obter todas as transações com base na tag e na data fornecida
    @Query("SELECT * FROM transaction_table WHERE tag = :tag AND strftime('%Y-%m', createdAt / 1000, 'unixepoch') = :monthYear ORDER BY createdAt DESC")
    fun getTransactionsByTagAndMonth(tag: String, monthYear: String): Flow<List<Transaction>>

    // Excluir uma transação pelo ID
    @Query("DELETE FROM transaction_table WHERE id = :id")
    suspend fun deleteById(id: Int)

    // Somar total de receitas do mês
    @Query("SELECT SUM(amount) FROM transaction_table WHERE tag = 'receita' AND strftime('%Y-%m', createdAt / 1000, 'unixepoch') = :monthYear")
    suspend fun getTotalRevenueForMonth(monthYear: String): Float?

    // Somar total de despesas do mês
    @Query("SELECT SUM(amount) FROM transaction_table WHERE tag = 'despesa' AND strftime('%Y-%m', createdAt / 1000, 'unixepoch') = :monthYear")
    suspend fun getTotalExpenseForMonth(monthYear: String): Float?

    // Somar total de reservas do mês
    @Query("SELECT SUM(amount) FROM transaction_table WHERE tag = 'reserva' AND strftime('%Y-%m', createdAt / 1000, 'unixepoch') = :monthYear")
    suspend fun getTotalReservationForMonth(monthYear: String): Float?
}
