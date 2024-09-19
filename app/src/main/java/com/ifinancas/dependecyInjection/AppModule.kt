package com.br.ifinancas.dependecyInjection

import android.content.Context
import androidx.room.Room
import com.br.ifinancas.db.AppDatabase
import com.br.ifinancas.db.models.FiModel.FiDao
import com.br.ifinancas.db.models.FiModel.FiRepository
import com.br.ifinancas.db.models.FiiNamesModel.FiisNameDao
import com.br.ifinancas.db.models.FiiNamesModel.FiisNameRepository
import com.br.ifinancas.db.models.PatrimonioModel.PatrimonioDao
import com.br.ifinancas.db.models.PatrimonioModel.PatrimonioRepository
import com.br.ifinancas.db.models.PatrimonioNamesModel.PatrimonioNamesDao
import com.br.ifinancas.db.models.PatrimonioNamesModel.PatrimonioNamesRepository
import com.br.ifinancas.db.models.TransactionModel.TransactionDao
import com.br.ifinancas.db.models.TransactionModel.TransactionRepository
import com.br.ifinancas.services.DateTimeService
import com.br.ifinancas.services.impl.DateTimeServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDateTimeService(@ApplicationContext context: Context): DateTimeService {
        return DateTimeServiceImpl(context)
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_database"
        )
            .fallbackToDestructiveMigration() // Importante ao mudar versões
            .build()
    }

    @Provides
    fun provideTransactionDao(database: AppDatabase): TransactionDao {
        return database.transactionDao()
    }

    @Provides
    @Singleton
    fun provideTransactionRepository(
        transactionDao: TransactionDao
    ): TransactionRepository {
        return TransactionRepository(transactionDao)
    }

    @Provides
    fun provideFiDao(database: AppDatabase): FiDao {
        return database.fiDao()
    }

    @Provides
    @Singleton
    fun provideFiRepository(fiDao: FiDao): FiRepository {
        return FiRepository(fiDao)
    }

    @Provides
    fun providePatrimonioDao(database: AppDatabase): PatrimonioDao {
        return database.patrimonioDao()
    }

    @Provides
    @Singleton
    fun providePatrimonioRepository(patrimonioDao: PatrimonioDao): PatrimonioRepository {
        return PatrimonioRepository(patrimonioDao)
    }

    @Provides
    fun provideFiisNameDao(database: AppDatabase): FiisNameDao {
        return database.fiisNameDao()
    }

    @Provides
    @Singleton
    fun provideFiisNameRepository(fileNameDao: FiisNameDao): FiisNameRepository {
        return FiisNameRepository(fileNameDao)
    }

    @Provides
    fun providePatrimonioNamesDao(database: AppDatabase): PatrimonioNamesDao {
        return database.patrimonioNameDao()
    }

    @Provides
    @Singleton
    fun providePatrimonioNamesRepository(patrimonioNamesDao: PatrimonioNamesDao): PatrimonioNamesRepository {
        return PatrimonioNamesRepository(patrimonioNamesDao)
    }
}
