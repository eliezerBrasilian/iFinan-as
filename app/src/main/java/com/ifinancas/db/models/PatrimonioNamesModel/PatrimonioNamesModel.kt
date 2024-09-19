package com.br.ifinancas.db.models.PatrimonioNamesModel

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "patrimonio_names_table")
data class PatrimonioNamesModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nome: String
)

