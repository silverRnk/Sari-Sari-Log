package com.example.sari_saristorelog.feature_transaction_log.presentation.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import com.example.sari_saristorelog.ui.theme.Surface1
import com.vanpra.composematerialdialogs.datetime.date.DatePickerColors

class MyDatePickerColor: DatePickerColors {
    override val calendarHeaderTextColor: Color
        get() = Color.Black
    override val headerBackgroundColor: Color
        get() = Surface1
    override val headerTextColor: Color
        get() = Color.Black

    private val _onSelectTextBackgroundColor = mutableStateOf(Surface1)
    private val _onUnselectTextBackgroundColor = mutableStateOf(Color.White)


    private val _onSelectedTextColor = mutableStateOf(Color.Black)
    private val _onUnselectedTextColor = mutableStateOf(Color.Gray)

    @Composable
    override fun dateBackgroundColor(active: Boolean): State<Color> {
        return if (active) _onSelectTextBackgroundColor else _onUnselectTextBackgroundColor
    }

    @Composable
    override fun dateTextColor(active: Boolean): State<Color> {
        return if(active) _onSelectedTextColor else _onUnselectedTextColor
    }
}
