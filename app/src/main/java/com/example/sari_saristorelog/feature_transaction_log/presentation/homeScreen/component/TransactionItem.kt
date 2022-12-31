package com.example.sari_saristorelog.feature_transaction_log.presentation.homeScreen.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sari_saristorelog.feature_transaction_log.domain.model.TransactionInfo
import java.time.format.DateTimeFormatter

@Composable
fun TransactionItem(
    transactionInfo: TransactionInfo,
    modifier: Modifier = Modifier
){
    val dateFormatter = DateTimeFormatter.ofPattern("MMM, dd yyyy hh:mm")
    val formattedCreatedDate = dateFormatter.format(transactionInfo.createdDate)
    val formattedConfirmedDate = if(transactionInfo.confirmedDate != null) dateFormatter.format(transactionInfo.confirmedDate) else ""


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

                Box(modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .border(width = 1.dp, color = Color.Black, shape = CircleShape)){
                    //Todo Change customer image implementation on Home Screen List
                    Icon(painter = painterResource(id = transactionInfo.customerIcon)
                        , contentDescription = "customerIcon",
                        modifier = Modifier
                            .size(40.dp)
                            .align(Alignment.Center))
                }


                Spacer(modifier = Modifier.width(10.dp))
                Column() {
                    Text(
                        text = transactionInfo.customerName,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.h3)

                    Text(
                        text = "Created: $formattedCreatedDate",
                        style = MaterialTheme.typography.h3,
                        fontSize = 12.sp)

                    Text(
                        text ="Confirmed: $formattedConfirmedDate",
                        style = MaterialTheme.typography.h3,
                        fontSize = 12.sp,
                        color = if (transactionInfo.isConfirmed) Color.Black else Color.Transparent)
                }

            }





            Text(text = transactionInfo.total.toString(), modifier = Modifier.padding(end= 20.dp), style = MaterialTheme.typography.h3)


        }
    }

}