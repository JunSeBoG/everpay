package com.junsebog.everpay.domain.model

data class Payer(
    val name: String,
    val surname: String,
    val email: String,
    val document: String,
    val documentType: String,
    val mobile: String,
)