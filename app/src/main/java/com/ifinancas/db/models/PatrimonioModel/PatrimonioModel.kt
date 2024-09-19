package com.br.ifinancas.db.models.PatrimonioModel

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.br.ifinancas.db.models.PatrimonioNamesModel.PatrimonioNamesModel

@Entity(
    tableName = "patrimonio_table",
    foreignKeys = [
        ForeignKey(
            entity = PatrimonioNamesModel::class,
            parentColumns = ["id"],
            childColumns = ["id_patrimonio_name"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["id_patrimonio_name"])]
)
data class PatrimonioModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val id_patrimonio_name: Int,
    val total: Float = 0f,
    val mes_ano: String,
)

data class PatrimonioWithName(
    @Embedded val patrimonioModel: PatrimonioModel,
    @Relation(
        parentColumn = "id_patrimonio_name",
        entityColumn = "id"
    )
    val patrimonioNames: PatrimonioNamesModel
)