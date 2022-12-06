package com.example.sari_saristorelog.feature_transaction_log.presentation.homeScreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sari_saristorelog.feature_transaction_log.domain.model.TransactionInfo
import com.example.sari_saristorelog.feature_transaction_log.domain.use_cases.TransactionLogUseCases
import com.example.sari_saristorelog.feature_transaction_log.domain.util.FilterBy
import com.example.sari_saristorelog.feature_transaction_log.domain.util.QueryOrder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
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

    private val _orderSeqeunce = mutableStateOf(QueryOrder.Desc)
    val orderSequence: State<QueryOrder> = _orderSeqeunce

    private val filterState = mutableStateOf<FilterBy>(FilterBy.NoFilter(_orderSeqeunce.value))

    private var getJob: Job? = null

    init {
        getTransactionInfo(filterState.value)
    }


    fun onEvent(event: HomeScreenEvent){
        when(event){
            is HomeScreenEvent.OnDateFilter -> {
                _onDateFilterState.value = onDateFilterState.value.copy(
                    fromDate = event.fromDate, toDate = event.toDate, isEnable = event.isEnable)
                updateFilterState()
                getTransactionInfo(filterState.value)
            }
            is HomeScreenEvent.OnSearchValueChange -> {
                if(event.searchBox.isNotBlank()){
                    _searchBoxState.value = searchBoxState.value.copy(
                        text = event.searchBox,
                        isEnable = true
                    )
                }else{
                    _searchBoxState.value = searchBoxState.value.copy(
                        text = "",
                        isEnable = false
                    )
                }
                updateFilterState()
                getTransactionInfo(filterState.value)

            }
            is HomeScreenEvent.OnSelectItem -> {

            }
        }

    }

    private fun getTransactionInfo(filterBy: FilterBy){
        getJob?.cancel()
        getJob = useCases.getTransactionInfoList.get(filterBy)
            .onEach { transactionInfos ->
                _transactionInfoList.value = transactionInfos
            }
            .launchIn(viewModelScope)
    }

    private fun updateFilterState(){
        if(_searchBoxState.value.isEnable && !_onDateFilterState.value.isEnable){
            filterState.value = FilterBy.Name(_searchBoxState.value.text, _orderSeqeunce.value)
        }else if(!_searchBoxState.value.isEnable && _onDateFilterState.value.isEnable){
            filterState.value = FilterBy.Date(
                fromDate = _onDateFilterState.value.fromDate,
                toDate = _onDateFilterState.value.toDate,
                queryOrder = _orderSeqeunce.value
            )
        }else if (_searchBoxState.value.isEnable && _onDateFilterState.value.isEnable){
            filterState.value = FilterBy.DateAndName(
                name = _searchBoxState.value.text,
                fromDate = _onDateFilterState.value.fromDate,
                toDate = _onDateFilterState.value.toDate,
                queryOrder = _orderSeqeunce.value
            )
        }else{
            filterState.value = FilterBy.NoFilter(_orderSeqeunce.value)
        }
    }

}