package com.junsebog.everpay.common

import com.junsebog.everpay.data.local.entity.TransactionEntity
import com.junsebog.everpay.data.remote.dto.TransactionDto
import com.junsebog.everpay.domain.model.Transaction
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.SimpleDateFormat
import java.util.*

object Extensions {
    fun TransactionDto.toTransaction() = Transaction(
        internalReference = this.internalReference,
        reference = this.reference,
        status = this.status.status,
        message = this.status.message,
        transactionDate = this.transactionDate,
        franchise = this.franchise,
        franchiseName = this.franchiseName,
        amount = this.amount.total,
        currency = this.amount.currency,
        lastDigits = this.lastDigits,
    )

    fun TransactionEntity.toTransaction() = Transaction(
        internalReference,
        status,
        message,
        transactionDate,
        reference,
        franchise,
        franchiseName,
        amount,
        currency,
        lastDigits,
    )

    fun Int.toCurrency(): String{
        val symbols = DecimalFormatSymbols().apply {
            groupingSeparator = '.'
            decimalSeparator = ','
        }
        val decimalFormat = DecimalFormat("#,###", symbols)
        return decimalFormat.format(this.toDouble())
    }

    fun String.toDate(): String{
        val date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX", Locale.getDefault()).parse(this)
        return SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(date)
    }

    fun String.toHour(): String{
        val date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX", Locale.getDefault()).parse(this)
        return SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(date)
    }
}