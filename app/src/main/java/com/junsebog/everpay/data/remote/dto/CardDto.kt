package com.junsebog.everpay.data.remote.dto

import com.junsebog.everpay.domain.model.Card

class CardDto(
    val number: String,
    val cvv: String,
    val expiration: String,
    val installments: Int
) {
    constructor(card: Card, ins: Int): this (
        number = card.number,
        cvv = card.cvv,
        expiration = card.expiration,
        installments = ins
    )
}