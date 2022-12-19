package com.example.sari_saristorelog.feature_transaction_log.presentation.AddEditTransaction.util

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.sari_saristorelog.R

sealed class DateTimeButtonSelection(@StringRes val contentDescription: Int, @DrawableRes val icon: Int){
    object Date: DateTimeButtonSelection(contentDescription = R.string.date_select_button1, icon = R.drawable.ic_date_range_24)
    object Time: DateTimeButtonSelection(contentDescription = R.string.date_select_button1, icon = R.drawable.ic_add_circle_outline_24)
    object Refresh: DateTimeButtonSelection(contentDescription = R.string.date_select_button3, icon = R.drawable.ic_refresh_24)
    object ArrowDown: DateTimeButtonSelection(contentDescription = R.string.date_select_button4, icon = R.drawable.ic_arrow_down_24)

    companion object {
        val list = listOf<DateTimeButtonSelection>(
            Date,
            Time,
            Refresh,
            ArrowDown
        )
    }
}
