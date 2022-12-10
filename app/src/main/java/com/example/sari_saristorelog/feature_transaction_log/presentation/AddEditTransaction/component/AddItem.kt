package com.example.sari_saristorelog.feature_transaction_log.presentation.AddEditTransaction.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun AddItem(
    modifier: Modifier = Modifier
){
    Box(modifier = modifier) {
        Canvas(modifier = Modifier.matchParentSize()){
            drawLine(
                color = Color.Black,
                start= Offset(0f, size.height),
                end = Offset(size.width, size.height),
                strokeWidth = 2f
            )
        }

        Icon(imageVector = Icons.Default.Add,
            contentDescription = "AddItem",
            modifier = Modifier
                .size(25.dp)
                .align(Alignment.CenterStart))
    }
}