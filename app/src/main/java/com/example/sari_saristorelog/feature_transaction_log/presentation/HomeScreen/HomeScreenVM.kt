package com.example.sari_saristorelog.feature_transaction_log.presentation.HomeScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf

import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sari_saristorelog.data.transaction.TransactionInfoAndCustomer
import com.example.sari_saristorelog.feature_transaction_log.domain.repository.LoggerRepository
import com.example.sari_saristorelog.feature_transaction_log.domain.util.QueryOrder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenVM @Inject constructor(
    repository: LoggerRepository
): ViewModel() {

    var transactionList by mutableStateOf(listOf<TransactionInfoAndCustomer>())
        private set

    init {
        viewModelScope.launch {
            transactionList = repository.getTransInfoWithCustomer(QueryOrder.Desc)
        }
    }



    var searchText by mutableStateOf("")
    private set

    fun onEvent(event: HomeScreenEvent){
        when(event){
            is HomeScreenEvent.OnFilterDate -> {

            }
            is HomeScreenEvent.OnSearchValueChange -> {

            }
            is HomeScreenEvent.OnSelectItem -> {

            }
        }

    }


}