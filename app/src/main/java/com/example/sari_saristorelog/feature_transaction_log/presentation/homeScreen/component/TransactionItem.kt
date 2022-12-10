package com.example.sari_saristorelog.feature_transaction_log.presentation.homeScreen.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sari_saristorelog.feature_transaction_log.domain.model.TransactionInfo

@Composable
fun TransactionItem(
    transactionInfo: TransactionInfo,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Box(modifier = modifier) {
        Canvas(modifier = Modifier.matchParentSize()){
            drawLine(color = Color.Black,
                start = Offset(0f, 1f),
                end = Offset(size.width, 1f),
            strokeWidth = 3f)

            drawLine(color = Color.Black,
                start = Offset(0f, size.height-1f),
                end = Offset(size.width, size.height-1f),
                strokeWidth = 1.5f)

        }

        Row(modifier = Modifier.matchParentSize(), verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween) {

            Row(verticalAlignment = Alignment.CenterVertically) {
                Spacer(modifier = Modifier.width(10.dp))

                Icon(painter = painterResource(id = com.example.sari_saristorelog.R.drawable.ic_baseline_face_24)
                    , contentDescription = "customerIcon",
                    modifier = Modifier
                        .height(50.dp)
                        .width(50.dp))

                Spacer(modifier = Modifier.width(10.dp))
                Column() {
                    Text(text = transactionInfo.customerName, maxLines = 1, overflow = TextOverflow.Ellipsis, style = MaterialTheme.typography.h3)
                    Text(text ="Created: " + transactionInfo.createdDate.toString(), style = MaterialTheme.typography.h3, fontSize = 12.sp)
                    Text(text ="Confirmed: " + transactionInfo.confirmedDate?.toString() ?: "", style = MaterialTheme.typography.h3, fontSize = 12.sp)
                }

            }





            Text(text = transactionInfo.total.toString(), modifier = Modifier.padding(end= 20.dp), style = MaterialTheme.typography.h3)


        }
    }

}