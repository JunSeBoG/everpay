package com.junsebog.everpay.data.local

import com.junsebog.everpay.common.Common.toTransaction
import com.junsebog.everpay.data.local.dao.TransactionDao
import com.junsebog.everpay.data.local.entity.TransactionEntity
import com.junsebog.everpay.domain.model.Transaction
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val dao: TransactionDao
) {

    fun getTransactionList(): Flow<List<Transaction>>{
        return dao.getTransactionList().map { transactionList ->
            println("Tmanio BB ${transactionList.size}")
            transactionList.map {
                it.toTransaction()
            }
        }
    }

    fun getTransaction(reference: Int): Flow<Transaction>{
        return dao.getTransaction(reference).map {
            it.toTransaction()
        }
    }

    fun insertTransaction(transaction: Transaction){
        println("GUARDANDDO BB")
        val transactionEntity = TransactionEntity(transaction)
        dao.insertTransaction(transactionEntity)
    }

    fun deleteTransaction(transaction: Transaction){
        val transactionEntity = TransactionEntity(transaction)
        dao.deleteTransaction(transactionEntity)
    }
}