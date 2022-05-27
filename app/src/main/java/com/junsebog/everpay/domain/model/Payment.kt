package com.junsebog.everpay.domain.model

class Payment(
    val payer: Payer,
    val paymentInfo: PaymentInfo,
    val card: Card,
    val installments: Int
)