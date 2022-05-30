package com.junsebog.everpay.presentation.ui.activity.payment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.junsebog.everpay.domain.model.*
import com.junsebog.everpay.domain.repository.TransactionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PaymentActivityFragmentViewModel @Inject constructor(
    private val repository: TransactionRepository
): ViewModel() {

    private var builder = Payment.Builder()

    private val _internalReference: MutableStateFlow<Int> = MutableStateFlow(0)
    val internalReference: StateFlow<Int>
        get() = _internalReference

    fun setProduct(product: Product){
        builder.product(product)
    }

    fun setPayer(name: String,
                 surname: String,
                 email: String,
                 document: String,
                 documentType: String,
                 mobile: String){

        builder.payer(Payer(name, surname, email, document, documentType, mobile))
    }

    fun setCard(number: String,
                cvv: String,
                expiration: String) {

        builder.card(Card(number, cvv, expiration))
    }

    fun setInstallments(installments: Int){
        builder.installments(installments)
    }

    private fun buildPayment(): Payment = builder.build()

    fun makeTransaction(){
        viewModelScope.launch(Dispatchers.IO) {
               _internalReference.value = repository.makePayment(buildPayment())
        }
    }
}