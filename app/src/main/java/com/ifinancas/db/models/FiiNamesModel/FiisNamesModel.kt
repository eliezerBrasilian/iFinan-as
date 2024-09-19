package com.br.ifinancas.db.models.FiiNamesModel

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fiis_name_table")
data class FiisNamesModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nome: String
)
