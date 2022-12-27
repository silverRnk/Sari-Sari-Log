package com.example.sari_saristorelog.feature_transaction_log.presentation.homeScreen

import androidx.compose.ui.focus.FocusState
import java.time.LocalDate

sealed class HomeScreenEvent{
    data class OnSelectItem(val transactionId: Int): HomeScreenEvent()
    data class OnNameTextFieldValueChange(val searchBox: String): HomeScreenEvent()
    data class OnNameTextFieldFocusChange(val focusState: FocusState): HomeScreenEvent()
    data class OnDateFilter(val fromDate: LocalDate, val toDate: LocalDate, val isEnable: Boolean): HomeScreenEvent()
    data class OnFromFilterDateSelected(val date: LocalDate): HomeScreenEvent()
    data class OnToFilterDateSelected(val date: LocalDate): HomeScreenEvent()
    object OnFilterVisibilityToggle: HomeScreenEvent()
}
