package com.ifinancas.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ifinancas.db.models.FiModel.FiDao
import com.ifinancas.db.models.FiModel.FiModel
import com.ifinancas.db.models.FiiNamesModel.FiisNameDao
import com.ifinancas.db.models.FiiNamesModel.FiisNamesModel
import com.ifinancas.db.models.PatrimonioModel.PatrimonioDao
import com.ifinancas.db.models.PatrimonioModel.PatrimonioModel
import com.ifinancas.db.models.PatrimonioNamesModel.PatrimonioNamesDao
import com.ifinancas.db.models.PatrimonioNamesModel.PatrimonioNamesModel
import com.ifinancas.db.models.TransactionModel.Converters
import com.ifinancas.db.models.TransactionModel.Transaction
import com.ifinancas.db.models.TransactionModel.TransactionDao

@Database(
    entities = [
        Transaction::class,
        FiModel::class,
        PatrimonioModel::class,
        FiisNamesModel::class,
        PatrimonioNamesModel::class],
    version = 9, exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun transactionDao(): TransactionDao
    abstract fun fiDao(): FiDao
    abstract fun patrimonioDao(): PatrimonioDao
    abstract fun fiisNameDao(): FiisNameDao
    abstract fun patrimonioNameDao(): PatrimonioNamesDao
}
