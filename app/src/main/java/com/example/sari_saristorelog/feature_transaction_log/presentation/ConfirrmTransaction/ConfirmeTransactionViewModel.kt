package com.example.sari_saristorelog.feature_transaction_log.presentation.ConfirrmTransaction

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sari_saristorelog.feature_transaction_log.domain.model.Transaction
import com.example.sari_saristorelog.feature_transaction_log.domain.use_cases.TransactionLogUseCases
import com.example.sari_saristorelog.feature_transaction_log.presentation.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConfirmTransactionViewModel @Inject constructor(
    private val useCases: TransactionLogUseCases,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _transactionState = mutableStateOf<Transaction?>(null)
    val transactionState: State<Transaction?> = _transactionState

    private val _confirmTransactionState = mutableStateOf<ConfirmTransactionState>(ConfirmTransactionState())
    val confirmTransactionState: State<ConfirmTransactionState> = _confirmTransactionState

    private val _uiState = MutableSharedFlow<UiState>()
    val uiState = _uiState.asSharedFlow()

    init {
        val transactionId = savedStateHandle.get<Long>("TransactionId")
        if (transactionId!!.toInt() != -1){
            viewModelScope.launch {
                useCases.getTransaction(transactionId)?.let {
                    _transactionState.value = it

                    Log.d("TransactionState", transactionState.value.toString())

                    if (it.transactionInfo.isConfirmed){
                        _confirmTransactionState.value = confirmTransactionState.value.copy(
                            isConfirmed = true
                        )
                    }else{
                        _confirmTransactionState.value = confirmTransactionState.value.copy(
                            isConfirmButtonEnable = true
                        )
                    }
                }

            }
        }
    }

    fun onEvent(event: ConfirmTransactionEvent){
        when(event){
            is ConfirmTransactionEvent.OnConfirmed -> {

                _transactionState.value = transactionState.value!!.copy(
                    transactionInfo = transactionState.value!!.transactionInfo.copy(
                        isConfirmed = true)
                )

                _confirmTransactionState.value = confirmTransactionState.value.copy(isConfirmed = true, isConfirmButtonEnable = false)
            }
            is ConfirmTransactionEvent.OnDelete -> {

            }
            is ConfirmTransactionEvent.OnEdit -> {

            }
        }
    }


}