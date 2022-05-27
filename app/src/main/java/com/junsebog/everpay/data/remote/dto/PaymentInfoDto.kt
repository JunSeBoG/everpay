package com.junsebog.everpay.data.remote.dto

import com.junsebog.everpay.domain.model.PaymentInfo

data class PaymentInfoDto(
    val amount: AmountDto,
    val description: String,
    val reference: String
){
    constructor(payment: PaymentInfo): this (
        amount = AmountDto(payment.currency, payment.total),
        description = payment.description,
        reference = payment.reference
    )
}