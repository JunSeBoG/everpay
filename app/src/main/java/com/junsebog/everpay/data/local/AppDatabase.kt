package com.junsebog.everpay.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.junsebog.everpay.data.local.dao.TransactionDao
import com.junsebog.everpay.data.local.entity.TransactionEntity

@Database(entities = [TransactionEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun transactionDao(): TransactionDao
}