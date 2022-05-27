package com.junsebog.everpay.data.remote.dto

import com.junsebog.everpay.domain.model.Card

data class InstrumentDto(
    val card: CardDto
){
    constructor(card: Card, ins: Int): this(
        card =  CardDto(card, ins)
    )
}