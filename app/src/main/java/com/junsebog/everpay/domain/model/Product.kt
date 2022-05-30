package com.junsebog.everpay.domain.model

import java.io.Serializable

data class Product(
    val total: Int,
    val currency: String = "COP",
    val description: String,
    val reference: String,
    val urlImage: Int = 0
): Serializable