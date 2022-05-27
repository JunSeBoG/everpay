package com.junsebog.everpay.common

import com.junsebog.everpay.data.local.entity.TransactionEntity
import com.junsebog.everpay.data.remote.dto.*
import com.junsebog.everpay.domain.model.Payment
import com.junsebog.everpay.domain.model.Transaction
import java.math.BigInteger
import java.security.MessageDigest
import java.security.SecureRandom
import java.text.SimpleDateFormat
import java.util.*

object Common {

    fun getRandom(): String {
        return BigInteger(130, SecureRandom()).toString()
    }

    fun getCurrentDateFormat(): String {
        return SimpleDateFormat("yyyy-MM-dd'T'HH:mmZ",
            Locale.getDefault()).format(Date())
    }

    fun getBase64(input: ByteArray): String {
        val encodedBytes: ByteArray = Base64.getEncoder().encode(input)

        return String(encodedBytes)
    }

    fun getSHA256(input: String): ByteArray {
        val mDigest: MessageDigest = MessageDigest.getInstance("SHA-256")
        return mDigest.digest(input.toByteArray())
    }

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
}