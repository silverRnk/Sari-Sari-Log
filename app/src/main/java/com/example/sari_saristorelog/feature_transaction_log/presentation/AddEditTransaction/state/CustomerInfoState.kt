package com.example.sari_saristorelog.feature_transaction_log.presentation.AddEditTransaction.state

import androidx.annotation.DrawableRes
import com.example.sari_saristorelog.R
import com.example.sari_saristorelog.feature_transaction_log.domain.model.TransactionInfo
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

data class CustomerInfoState(
    val info: TransactionInfo = TransactionInfo(
        customerName = "",
        customerIcon = R.drawable.ic_baseline_face_24,
        createdDate = LocalDateTime.of(LocalDate.now(), LocalTime.now()),
        total = 0.0),
    val isInputError: Boolean = false,
    val isDateStatusVisible: Boolean = false
)
