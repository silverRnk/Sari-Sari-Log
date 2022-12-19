package com.example.sari_saristorelog.feature_transaction_log.presentation.AddEditTransaction.state

import androidx.annotation.DrawableRes
import com.example.sari_saristorelog.R

data class CustomerInfoState(
    val name: String = "",
    @DrawableRes val customerIcon: Int = R.drawable.ic_baseline_face_24,
    val isInputError: Boolean = false
)
