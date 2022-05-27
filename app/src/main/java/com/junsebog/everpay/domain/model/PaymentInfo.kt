package com.junsebog.everpay.domain.model

data class PaymentInfo(
    val total: Int,
    val currency: String,
    val description: String,
    val reference: String
)