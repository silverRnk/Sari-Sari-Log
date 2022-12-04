package com.example.sari_saristorelog.feature_transaction_log.presentation.HomeScreen.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.sari_saristorelog.data.transaction.TransactionInfoAndCustomer
import com.example.sari_saristorelog.feature_transaction_log.presentation.HomeScreen.HomeScreenEvent
import com.example.sari_saristorelog.ui.theme.appTypography
import java.util.*

@Composable
fun TransactionItem(
    transactionItem: TransactionInfoAndCustomer,
    OnClick: (HomeScreenEvent) -> Unit
){
    val borderStroke = BorderStroke(width = 2.dp, color = Color.Black)

    //Date @Todo Edit date from long to datetime
    val createdDate = Date(transactionItem.createdDate)
    val confirmedDate: Date? = if(transactionItem.confirmedDate != null) Date(transactionItem.confirmedDate) else null

    Card(modifier = Modifier
        .height(200.dp)
        .fillMaxWidth(0.9f)
        .clickable { OnClick(HomeScreenEvent.OnSelectItem(transactionItem.transactionId ?: 0)) },
    border = borderStroke) {

        Row(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
            verticalAlignment = Alignment.CenterVertically) {

            Image(painter = painterResource(id = transactionItem.avatar), contentDescription = "Customer Avatar"
            , modifier = Modifier
                    .height(25.dp)
                    .width(25.dp), contentScale = ContentScale.Fit)

            Column(horizontalAlignment = Alignment.Start, verticalArrangement = Arrangement.Center) {
                Text(text = transactionItem.nickName, style = appTypography.h2)
                Text(text = createdDate.toString(), style = appTypography.h3)
                Text(text = confirmedDate?.toString()?: "", style = appTypography.h3)
            }

            Text(text = transactionItem.total.toString(), style = appTypography.h2)

        }



    }

}