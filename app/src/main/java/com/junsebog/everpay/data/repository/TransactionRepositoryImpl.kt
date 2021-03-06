package com.junsebog.everpay.data.repository

import com.junsebog.everpay.data.local.LocalDataSource
import com.junsebog.everpay.data.remote.RemoteDataSource
import com.junsebog.everpay.domain.model.Payment
import com.junsebog.everpay.domain.model.Transaction
import com.junsebog.everpay.domain.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
): TransactionRepository {

    override fun getTransactionByReference(reference: Int): Flow<Transaction> {
        return localDataSource.getTransaction(reference)
    }

    override fun getTransactionList(): Flow<List<Transaction>> {
        return localDataSource.getTransactionList()
    }

    override suspend fun makePayment(payment: Payment): Int {
        var internalReference = 0

        remoteDataSource.makePayment(payment).collect{
            localDataSource.insertTransaction(it)
            internalReference = it.internalReference
        }

        return internalReference
    }

    override suspend fun deleteTransaction(transaction: Transaction) {
        localDataSource.deleteTransaction(transaction)
    }
}