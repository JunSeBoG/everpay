package com.junsebog.everpay.data.local

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.junsebog.everpay.data.local.dao.TransactionDao
import com.junsebog.everpay.data.local.entity.TransactionEntity

@Database(entities = [TransactionEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {


    abstract fun transactionDao(): TransactionDao

    companion object{
        private const val DATABASE_NAME = "everpay_db"

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase{
            val actualInstance = INSTANCE
            if (actualInstance != null){
                return actualInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    DATABASE_NAME).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}