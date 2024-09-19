package com.br.ifinancas.db.models.FiModel

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.br.ifinancas.db.models.FiiNamesModel.FiisNamesModel

@Entity(
    tableName = "fii_table",
    foreignKeys = [
        ForeignKey(
            entity = FiisNamesModel::class,
            parentColumns = ["id"],
            childColumns = ["id_fiis_name"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["id_fiis_name"])]  // Importante para otimizar buscas e forçar a integridade referencial
)
data class FiModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val id_fiis_name: Int,
    val cotas: Int = 0,
    val mes_ano: String,
    val proventos: Float = 0f
)

data class FiiWithName(
    @Embedded val fii: FiModel,
    @Relation(
        parentColumn = "id_fiis_name",
        entityColumn = "id"
    )
    val fiisName: FiisNamesModel
)

