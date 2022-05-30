package com.junsebog.everpay.presentation.ui.fragment.transaction

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.junsebog.everpay.domain.model.Transaction
import com.junsebog.everpay.domain.repository.TransactionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class TransactionFragmentViewModel @Inject constructor(
    private val repository: TransactionRepository
): ViewModel() {

    private val _transaction: MutableStateFlow<Transaction> = MutableStateFlow(Transaction())
    val transaction: StateFlow<Transaction>
        get() = _transaction

    fun getTransaction(reference: Int){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getTransactionByReference(reference).collect{
                _transaction.value = it
            }
        }
    }

    fun deleteTransaction(){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.deleteTransaction(transaction.value)
            }catch (e: Exception){
            }
        }
    }
}