package com.example.sari_saristorelog.feature_transaction_log.presentation.AddEditTransaction.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.sari_saristorelog.feature_transaction_log.domain.model.Items

@Composable
fun Item(
    modifier: Modifier = Modifier,
    items: Items,
    OnDeleteItem: () -> Unit
){

    Box(modifier = modifier) {
        Canvas(modifier = Modifier.matchParentSize()){
            drawLine(
                color = Color.Black,
                start= Offset(0f, size.width),
                end = Offset(size.height, size.width),
                strokeWidth = 0.3f
            )
        }

        Row(modifier = Modifier.matchParentSize()) {

            Box(modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(0.5f)){
                Text(
                    text = items.description,
                    style = MaterialTheme.typography.h3,
                    maxLines = 1,
                    overflow = TextOverflow.Clip,
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .fillMaxHeight()
                        .padding(start = 5.dp)
                        .align(Alignment.CenterStart))
            }


            Box(modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(0.15f)) {
                Canvas(modifier = Modifier
                    .matchParentSize()
                    .align(Alignment.CenterStart)){
                    drawLine(
                        color = Color.Black,
                        start = Offset(0f, 0f),
                        end = Offset(0f, size.height - (size.height/0.15f))
                    )
                }

                Text(
                    text = items.quantity.toString(),
                    style = MaterialTheme.typography.h3,
                    maxLines = 1,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .align(Alignment.Center))

                Canvas(modifier = Modifier
                    .matchParentSize()
                    .align(Alignment.CenterEnd)){
                    drawLine(
                        color = Color.Black,
                        start = Offset(size.width, 0f),
                        end = Offset(size.width, size.height - (size.height/0.15f))
                    )
                }
            }

            Box(modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(0.25f)){
                Text(
                    text = items.description,
                    style = MaterialTheme.typography.h3,
                    maxLines = 1,
                    overflow = TextOverflow.Clip,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .fillMaxHeight()
                        .align(Alignment.Center))
            }

            Box(modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(0.1f)){

                IconButton(onClick = {OnDeleteItem} ) {
                    Icon(imageVector = Icons.Default.Close,
                        contentDescription = "Delete",
                        modifier = Modifier
                            .height(15.dp)
                            .width(15.dp))

                }

            }

        }
    }


}