package com.junsebog.everpay.data.remote

import com.junsebog.everpay.data.remote.dto.PaymentDto
import com.junsebog.everpay.data.remote.dto.TransactionDto
import retrofit2.http.Body
import retrofit2.http.POST

interface EvertecApi {

    @POST("gateway/process")
    suspend fun makeTransaction(@Body body: PaymentDto): TransactionDto
}