package com.example.sari_saristorelog.feature_transaction_log.presentation.homeScreen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf

import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sari_saristorelog.feature_transaction_log.domain.model.TransactionInfo
import com.example.sari_saristorelog.feature_transaction_log.domain.repository.LoggerRepository
import com.example.sari_saristorelog.feature_transaction_log.domain.use_cases.TransactionLogUseCases
import com.example.sari_saristorelog.feature_transaction_log.domain.util.FilterBy
import com.example.sari_saristorelog.feature_transaction_log.domain.util.QueryOrder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenVM @Inject constructor(
   private val useCases: TransactionLogUseCases
): ViewModel()
{


    private val _transactionInfoList = mutableStateOf(listOf<TransactionInfo>())
    val transactionInfoList: State<List<TransactionInfo>> = _transactionInfoList

    private val _searchBoxState = mutableStateOf(TextFieldState())
    val searchBoxState: State<TextFieldState> = _searchBoxState

    private val _onDateFilterState = mutableStateOf(DateFilterState())
    val onDateFilterState: State<DateFilterState> = _onDateFilterState

    private var getJob: Job? = null

    init {
        getTransactionInfo(FilterBy.NoFilter(order = QueryOrder.Desc))
    }


    fun onEvent(event: HomeScreenEvent){
    }

    private fun getTransactionInfo(filterBy: FilterBy){
        getJob?.cancel()
        getJob = useCases.getTransactionInfoList.get(filterBy)
            .onEach { transactionInfos ->
                _transactionInfoList.value = transactionInfos
            }
            .launchIn(viewModelScope)
    }

}