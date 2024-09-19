package com.br.ifinancas.db.models.PatrimonioModel

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PatrimonioRepository @Inject constructor(private val patrimonioDao: PatrimonioDao) {

    fun getAll(monthYear: String): Flow<List<PatrimonioWithName>> {
        return patrimonioDao.getAll(monthYear)
    }

    suspend fun insert(patrimonioModel: PatrimonioModel) {
        patrimonioDao.insert(patrimonioModel)
    }

    suspend fun update(patrimonioModel: PatrimonioModel) {
        return patrimonioDao.update(patrimonioModel)
    }

    suspend fun getPatrimonioTotal(monthYear: String): Float {
        return patrimonioDao.getPatrimonioTotal(monthYear) ?: 0f
    }

    suspend fun delete(id: Int) {
        return patrimonioDao.deleteById(id)
    }
}
