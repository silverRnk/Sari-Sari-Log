package com.example.sari_saristorelog.feature_transaction_log.presentation.AddEditTransaction.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.sari_saristorelog.feature_transaction_log.domain.model.Items
import java.math.RoundingMode
import java.text.DecimalFormat

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
                start= Offset(0f, size.height),
                end = Offset(size.width, size.height),
                strokeWidth = 3f
            )
        }

        Row(modifier = Modifier.matchParentSize()) {

            Box(modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(0.5f)){


                Row(modifier = Modifier.matchParentSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start) {
                    Text(
                        text = items.description,
                        style = MaterialTheme.typography.h3,
                        maxLines = 1,
                        textAlign = TextAlign.Start,
                        overflow = TextOverflow.Clip,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 5.dp))
                }

            }


            Box(modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(0.35f)) {
                Canvas(modifier = Modifier
                    .matchParentSize()
                    .align(Alignment.CenterStart)){

                    drawLine(
                        color = Color.Black,
                        start = Offset(0f, size.height*0.1f),
                        end = Offset(0f, size.height - size.height*0.1f),
                        strokeWidth = 3f
                    )

                    drawLine(
                        color = Color.Black,
                        start = Offset(size.width, size.height*0.1f),
                        end = Offset(size.width, size.height - (size.height*0.1f)),
                        strokeWidth = 3f
                    )
                }

                Row(modifier = Modifier.matchParentSize(), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = items.quantity.toString(),
                        style = MaterialTheme.typography.h3,
                        maxLines = 1,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth(0.8f))
                }

            }

            Box(modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(0.8f)){
                val subtotalFormat = DecimalFormat("#.##")
                subtotalFormat.roundingMode = RoundingMode.DOWN

                Row(modifier = Modifier.matchParentSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center) {
                    Text(
                        text = subtotalFormat.format(items.subtotal),
                        style = MaterialTheme.typography.h3,
                        maxLines = 1,
                        overflow = TextOverflow.Clip,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth())
                }

            }

            Box(modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()) {

                Icon(imageVector = Icons.Default.Close,
                    contentDescription = "Delete",
                    modifier = Modifier
                        .size(20.dp)
                        .clickable { OnDeleteItem() }
                        .align(Alignment.Center))

            }

        }
    }

}