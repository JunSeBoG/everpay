package com.junsebog.everpay.data.remote

import com.junsebog.everpay.common.Extensions.toTransaction
import com.junsebog.everpay.data.remote.dto.PaymentDto
import com.junsebog.everpay.domain.model.Payment
import com.junsebog.everpay.domain.model.Transaction
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val evertecApi: EvertecApi
){
    fun makePayment(payment: Payment): Flow<Transaction>{
        val paymentDto = PaymentDto(payment)
        return flow {
            emit(evertecApi.makeTransaction(paymentDto))
        }.map {
            it.toTransaction()
        }
    }
}