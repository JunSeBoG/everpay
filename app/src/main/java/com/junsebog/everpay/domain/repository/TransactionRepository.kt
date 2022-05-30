package com.junsebog.everpay.domain.repository

import com.junsebog.everpay.domain.model.Payment
import com.junsebog.everpay.domain.model.Transaction
import kotlinx.coroutines.flow.Flow

interface TransactionRepository {
    fun getTransactionByReference(reference: Int): Flow<Transaction>
    fun getTransactionList(): Flow<List<Transaction>>
    suspend fun makePayment(payment: Payment): Int
    suspend fun deleteTransaction(transaction: Transaction)
}