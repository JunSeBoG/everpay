package com.junsebog.everpay.data.remote.dto

data class TransactionDto(
    val internalReference: Int = 0,
    val reference: String = "",
    val status: StatusDto,
    val amount: AmountDto,
    val franchise: String = "",
    val franchiseName: String = "",
    val lastDigits: String = "",
    val provider: String = "",
    val transactionDate: String = ""
)