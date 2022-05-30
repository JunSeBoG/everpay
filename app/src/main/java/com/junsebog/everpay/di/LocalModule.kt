package com.junsebog.everpay.di

import android.content.Context
import androidx.room.Room
import com.junsebog.everpay.data.local.AppDatabase
import com.junsebog.everpay.data.local.dao.TransactionDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase = Room.databaseBuilder(context, AppDatabase::class.java, "everpar_db").build()

    @Provides
    fun providesTransactionDao(db: AppDatabase): TransactionDao = db.transactionDao()
}