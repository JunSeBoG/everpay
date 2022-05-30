package com.junsebog.everpay.presentation.ui.fragment.transactions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.junsebog.everpay.domain.model.Transaction
import com.junsebog.everpay.domain.repository.TransactionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionsFragmentViewModel @Inject constructor(
    private val repository: TransactionRepository
): ViewModel() {

    private val _transactionList: MutableStateFlow<List<Transaction>> = MutableStateFlow(emptyList())
    val transactionList: StateFlow<List<Transaction>>
        get() = _transactionList

    fun getTransactionList(){

        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.getTransactionList().collect{
                    _transactionList.value = it
                }
            }catch (e: Exception){

            }
        }
    }
}