package com.example.sari_saristorelog.feature_transaction_log.presentation.homeScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf

import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sari_saristorelog.feature_transaction_log.domain.model.TransactionInfo
import com.example.sari_saristorelog.feature_transaction_log.domain.model.TransactionInfoAndCustomer
import com.example.sari_saristorelog.feature_transaction_log.domain.repository.LoggerRepository
import com.example.sari_saristorelog.feature_transaction_log.domain.util.QueryOrder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenVM @Inject constructor(
    repository: LoggerRepository
): ViewModel() {

    var transactionList by mutableStateOf(listOf<TransactionInfo>())
    private set

    var searchText by mutableStateOf("")
    private set



    init {

        viewModelScope.launch {
            repository.getTransInfo(order = QueryOrder.Desc).onEach {
                transactionList = it }
        }

    }

    fun onEvent(event: HomeScreenEvent){
    }

}