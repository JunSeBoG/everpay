package com.junsebog.everpay.domain.model

class Payment(
    val payer: Payer,
    val product: Product,
    val card: Card,
    val installments: Int
){

    class Builder{
        private lateinit var payer: Payer
        private lateinit var product: Product
        private lateinit var card: Card
        private var installments: Int = 0

        fun payer(payer: Payer) = apply{
            this.payer = payer
        }

        fun product(product: Product) = apply {
            this.product = product
        }

        fun card(card: Card) = apply{
            this.card = card
        }

        fun installments(installments: Int) = apply{
            this.installments = installments
        }

        fun build() = Payment(
            payer = payer,
            product = product,
            card = card,
            installments = installments
        )
    }
}