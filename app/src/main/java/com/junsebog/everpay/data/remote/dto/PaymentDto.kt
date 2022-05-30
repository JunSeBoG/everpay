package com.junsebog.everpay.data.remote.dto

import com.junsebog.everpay.domain.model.Payer
import com.junsebog.everpay.domain.model.Payment

data class PaymentDto(
    val auth: Auth = Auth(),
    val payer: Payer,
    val payment: PaymentInfoDto,
    val instrument: InstrumentDto
){
    constructor(paymentParam: Payment):this(
        payer = paymentParam.payer,
        payment = PaymentInfoDto(paymentParam.product),
        instrument = InstrumentDto(paymentParam.card, paymentParam.installments)
    )
}
