package com.example.sari_saristorelog.feature_transaction_log.presentation.homeScreen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.unit.dp

@Composable
fun NameFilterTextField(
    text: String,
    placeholder: String,
    isPlaceholderVisible: Boolean,
    onValueChange: (String) -> Unit,
    onFocusChange: (FocusState) -> Unit,
    modifier: Modifier = Modifier
){
    Box(modifier = modifier){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .matchParentSize()
                .padding(5.dp)) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
                tint = Color.Gray,
                modifier = Modifier.fillMaxWidth(0.1f))
            Box(modifier = Modifier
                .fillMaxWidth()) {
                BasicTextField(
                    value = text,
                    onValueChange = onValueChange,
                    textStyle = MaterialTheme.typography.body1,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Center)
                        .onFocusChanged { onFocusChange(it) })

                Text(
                    text = placeholder,
                    style = MaterialTheme.typography.body1,
                    color = if (isPlaceholderVisible) Color.Gray else Color.Transparent,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Center))
            }

        }

    }
}