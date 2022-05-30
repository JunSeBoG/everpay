package com.junsebog.everpay.domain.model

class Transaction(
    val internalReference: Int = 0,
    val status: String = "",
    val message: String = "",
    val transactionDate: String = "",
    val reference: String = "",
    val franchise: String = "",
    val franchiseName: String = "",
    val amount: Int = 0,
    val currency: String = "",
    val lastDigits: String = ""
)