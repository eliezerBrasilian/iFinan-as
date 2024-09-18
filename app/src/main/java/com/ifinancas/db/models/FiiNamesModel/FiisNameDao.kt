package com.ifinancas.db.models.FiiNamesModel

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FiisNameDao {
    @Insert
    suspend fun insert(fiisNamesModel: FiisNamesModel): Long

    @Query("SELECT * FROM fiis_name_table ORDER BY id DESC")
    fun getAll(): Flow<List<FiisNamesModel>>
}