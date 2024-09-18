package com.ifinancas.db.models.PatrimonioModel

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface PatrimonioDao {

    @Insert
    suspend fun insert(patrimonioModel: PatrimonioModel)

    @Update
    suspend fun update(patrimonioModel: PatrimonioModel)

    // Excluir uma transação pelo ID
    @Query("DELETE FROM patrimonio_table WHERE id = :id")
    suspend fun deleteById(id: Int)

    // Consulta para obter as transações com o nome correspondente
    @Transaction
    @Query("SELECT * FROM patrimonio_table WHERE mes_ano = :monthYear ORDER BY id DESC")
    fun getAll(monthYear: String): Flow<List<PatrimonioWithName>>

    @Query("SELECT SUM(total) FROM patrimonio_table WHERE mes_ano = :monthYear")
    suspend fun getPatrimonioTotal(monthYear: String): Float?
}
