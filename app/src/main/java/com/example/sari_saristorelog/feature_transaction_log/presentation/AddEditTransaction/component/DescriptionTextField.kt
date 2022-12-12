package com.example.sari_saristorelog.feature_transaction_log.presentation.AddEditTransaction.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun DescriptionTextField(
    description: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
){
    Box(modifier = modifier){
        BasicTextField(
            value = description,
            onValueChange = onValueChange,
            textStyle = MaterialTheme.typography.h2,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next),
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
                .align(Alignment.Center)
        )

    }

}