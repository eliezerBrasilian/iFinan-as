package com.ifinancas.db.models.FiModel

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface FiDao {
    @Insert
    suspend fun insert(fiModel: FiModel)

    @Update
    suspend fun update(fiModel: FiModel)

    // Excluir uma transação pelo ID
    @Query("DELETE FROM transaction_table WHERE id = :id")
    suspend fun deleteById(id: Int)

    // Consulta para obter as transações com o nome correspondente
    @Transaction
    @Query("SELECT * FROM fii_table WHERE mes_ano = :monthYear ORDER BY id DESC")
    fun getFiisWithName(monthYear: String): Flow<List<FiiWithName>>
}
