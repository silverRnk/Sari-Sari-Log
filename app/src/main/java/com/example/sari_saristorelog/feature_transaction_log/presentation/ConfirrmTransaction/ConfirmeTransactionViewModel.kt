package com.example.sari_saristorelog.feature_transaction_log.presentation.ConfirrmTransaction

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.sari_saristorelog.feature_transaction_log.domain.use_cases.TransactionLogUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ConfirmTransactionViewModel @Inject constructor(
    private val useCases: TransactionLogUseCases,
    savedStateHandle: SavedStateHandle
): ViewModel() {



}