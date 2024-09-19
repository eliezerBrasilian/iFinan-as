package com.br.ifinancas.db.models.PatrimonioNamesModel

import javax.inject.Inject

class PatrimonioNamesRepository
@Inject constructor(private val patrimonioNamesDao: PatrimonioNamesDao) {
    val all = patrimonioNamesDao.getAll()

    suspend fun insert(patrimonioNamesModel: PatrimonioNamesModel): Int {
        return patrimonioNamesDao.insert(patrimonioNamesModel).toInt()
    }
}