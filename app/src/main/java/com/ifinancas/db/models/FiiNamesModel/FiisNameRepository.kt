package com.ifinancas.db.models.FiiNamesModel

import javax.inject.Inject

class FiisNameRepository
@Inject constructor(private val fiisNameDao: FiisNameDao) {
    val allFiisName = fiisNameDao.getAll()

    suspend fun insert(fiisNamesModel: FiisNamesModel): Int {
        return fiisNameDao.insert(fiisNamesModel).toInt()
    }
}