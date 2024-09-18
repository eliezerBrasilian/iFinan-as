package com.ifinancas.db.models.PatrimonioNamesModel

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PatrimonioNamesDao {
    @Insert
    suspend fun insert(patrimonioNamesModel: PatrimonioNamesModel): Long

    @Query("SELECT * FROM patrimonio_names_table ORDER BY id DESC")
    fun getAll(): Flow<List<PatrimonioNamesModel>>
}