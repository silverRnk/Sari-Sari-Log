package com.example.sari_saristorelog.feature_transaction_log.presentation.homeScreen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.sari_saristorelog.feature_transaction_log.domain.model.TransactionInfo

@Composable
fun TransactionItem(
    transactionInfo: TransactionInfo,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Box(modifier = Modifier
        .background(if (!transactionInfo.isConfirmed) Color.White else Color.LightGray)
        .wrapContentSize()) {
        Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
            Spacer(modifier = Modifier.width(1.dp))

            //@Todo Add Custom Icon
            Icon(painter = painterResource(id = transactionInfo.customerIcon), contentDescription = "customerIcon")

            Spacer(modifier = Modifier.width(2.dp))
            Column() {
                Text(text = transactionInfo.customerName, maxLines = 1, overflow = TextOverflow.Ellipsis)
                Text(text = transactionInfo.createdDate.toString())
                Text(text = transactionInfo.confirmedDate.toString())
            }

            Spacer(modifier = Modifier.width(10.dp))

            Text(text = transactionInfo.total.toString())


        }
    }

}