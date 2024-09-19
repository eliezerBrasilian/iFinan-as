package com.br.ifinancas.db.models.FiModel

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FiRepository @Inject constructor(private val fiDao: FiDao) {

    fun getFiisWithName(monthYear: String): Flow<List<FiiWithName>> {
        return fiDao.getFiisWithName(monthYear)
    }

    suspend fun insert(fiModel: FiModel) {
        return fiDao.insert(fiModel)
    }

    suspend fun update(fiModel: FiModel) {
        return fiDao.update(fiModel)
    }
}
