package com.junsebog.everpay.data.remote.dto

import com.junsebog.everpay.domain.model.Product

data class PaymentInfoDto(
    val amount: AmountDto,
    val description: String,
    val reference: String
){
    constructor(product: Product): this (
        amount = AmountDto(product.currency, product.total),
        description = product.description,
        reference = product.reference
    )
}