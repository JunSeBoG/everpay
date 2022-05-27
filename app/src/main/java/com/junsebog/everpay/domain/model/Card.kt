package com.junsebog.everpay.domain.model

data class Card(
    val number: String,
    val cvv: String,
    val expiration: String
)