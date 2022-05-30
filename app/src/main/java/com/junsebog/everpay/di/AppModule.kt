package com.junsebog.everpay.di

import com.junsebog.everpay.data.repository.TransactionRepositoryImpl
import com.junsebog.everpay.domain.repository.TransactionRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    abstract fun getRepository(repositoryImpl: TransactionRepositoryImpl): TransactionRepository
}