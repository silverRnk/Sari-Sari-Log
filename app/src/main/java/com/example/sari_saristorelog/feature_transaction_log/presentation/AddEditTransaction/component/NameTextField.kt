package com.example.sari_saristorelog.feature_transaction_log.presentation.AddEditTransaction.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun NameTextField(
    text: String,
    onValueChange: (String) -> Unit
){
    Box(
        modifier = Modifier
            .wrapContentHeight()
            .clip(RoundedCornerShape(5.dp))
            .fillMaxWidth(0.8f)
            .background(Color.White)) {

        BasicTextField(
            value = text,
            onValueChange = {onValueChange(it)},
            textStyle = MaterialTheme.typography.body1,
            singleLine = true,
            maxLines = 1,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 5.dp, top = 5.dp))
    }

}