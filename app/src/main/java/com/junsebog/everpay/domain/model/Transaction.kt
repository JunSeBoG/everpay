package com.junsebog.everpay.domain.model

class Transaction(
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
)