package com.example.sari_saristorelog.feature_transaction_log.presentation.homeScreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sari_saristorelog.feature_transaction_log.domain.model.TransactionInfo
import com.example.sari_saristorelog.feature_transaction_log.domain.use_cases.TransactionLogUseCases
import com.example.sari_saristorelog.feature_transaction_log.domain.util.FilterBy
import com.example.sari_saristorelog.feature_transaction_log.domain.util.QueryOrder
import com.example.sari_saristorelog.feature_transaction_log.presentation.homeScreen.state.DateFilterState
import com.example.sari_saristorelog.feature_transaction_log.presentation.homeScreen.state.TextFieldState
import com.example.sari_saristorelog.feature_transaction_log.presentation.util.DateConverter.convertLocalDateToLocalDateTime
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

    private val filterState = mutableStateOf<FilterBy>(
        FilterBy.Date(onDateFilterState.value.fromDate, onDateFilterState.value.toDate,
        orderSequence.value))

    private var getJob: Job? = null

    init {
        getTransactionInfo(filterState.value)
    }


    fun onEvent(event: HomeScreenEvent){
        when(event){
            is HomeScreenEvent.OnDateFilter -> {
                _onDateFilterState.value = onDateFilterState.value.copy(
                    fromDate = convertLocalDateToLocalDateTime(event.fromDate),
                    toDate = convertLocalDateToLocalDateTime(event.toDate),
                    isEnable = event.isEnable)
                updateFilterState()
                getTransactionInfo(filterState.value)
            }
            is HomeScreenEvent.OnNameTextFieldValueChange -> {
                if(event.searchBox.isNotBlank()){
                    _searchBoxState.value = searchBoxState.value.copy(
                        text = event.searchBox,
                        isEnable = true,
                        isPlaceHolderVisible = false
                    )
                }else{
                    _searchBoxState.value = searchBoxState.value.copy(
                        text = "",
                        isEnable = false,
                        isPlaceHolderVisible = true
                    )
                }
                updateFilterState()
                getTransactionInfo(filterState.value)

            }
            is HomeScreenEvent.OnNameTextFieldFocusChange -> {
                _searchBoxState.value = searchBoxState.value.copy(
                    isPlaceHolderVisible = !event.focusState.isFocused || searchBoxState.value.text.isEmpty())
            }
            is HomeScreenEvent.OnSelectItem -> {
                //@Todo Implement HomeScreenEvent OnSelectItem
            }
            is HomeScreenEvent.OnFromFilterDateSelected -> {
                _onDateFilterState.value = onDateFilterState.value.copy(
                    fromDate = convertLocalDateToLocalDateTime(event.date)
                )
            }
            is HomeScreenEvent.OnToFilterDateSelected -> {
                _onDateFilterState.value = onDateFilterState.value.copy(
                    toDate = convertLocalDateToLocalDateTime(event.date)
                )
                updateFilterState()
                getTransactionInfo(filterState.value)
            }
            is HomeScreenEvent.OnFilterVisibilityToggle -> {
                _onDateFilterState.value = onDateFilterState.value.copy(
                    isEnable = !onDateFilterState.value.isEnable
                )
                updateFilterState()
                getTransactionInfo(filterState.value)
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
        if (!searchBoxState.value.isEnable && searchBoxState.value.text.isEmpty()){
            filterState.value = FilterBy.Date(
                fromDate = onDateFilterState.value.fromDate,
                toDate = onDateFilterState.value.toDate,
                queryOrder = orderSequence.value)
        }else{
            filterState.value = FilterBy.DateAndName(
                name = searchBoxState.value.text,
                fromDate = onDateFilterState.value.fromDate,
                toDate = onDateFilterState.value.toDate,
                queryOrder = orderSequence.value)
        }
    }



}

