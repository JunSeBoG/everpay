package com.junsebog.everpay.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.junsebog.everpay.data.local.entity.TransactionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {

    @Insert(onConflict = REPLACE)
    fun insertTransaction(transaction: TransactionEntity)

    @Query("SELECT * FROM `transaction`")
    fun getTransactionList(): Flow<List<TransactionEntity>>

    @Query("SELECT * FROM `transaction` WHERE internalReference = :reference")
    fun getTransaction(reference: Int): Flow<TransactionEntity>

    @Delete
    fun deleteTransaction(transaction: TransactionEntity)
}