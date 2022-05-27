package com.junsebog.everpay.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.junsebog.everpay.domain.model.Transaction

@Entity(tableName = "transaction")
data class TransactionEntity(
    @PrimaryKey
    val internalReference: Int,
    val status: String,
    val message: String,
    val transactionDate: String,
    val reference: String,
    val franchise: String,
    val franchiseName: String,
    val amount: Int,
    val currency: String,
    val lastDigits: String
){
    constructor(transaction: Transaction) : this (
        internalReference = transaction.internalReference,
        status = transaction.status,
        message = transaction.message,
        transactionDate = transaction.transactionDate,
        reference = transaction.reference,
        franchise = transaction.franchise,
        franchiseName = transaction.franchiseName,
        amount = transaction.amount,
        currency = transaction.currency,
        lastDigits = transaction.lastDigits
    )
}