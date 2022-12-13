package com.example.sari_saristorelog.feature_transaction_log.presentation.AddEditTransaction.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sari_saristorelog.ui.theme.Surface1

@Composable
fun AddItemDialog(
    description: String,
    quantity: String,
    price: String,
    subtotal: String,
    onDescriptionChange: (String) -> Unit,
    onQuantityChange: (String) -> Unit,
    onPriceChange: (String) -> Unit,
    onSubtotalChange: (String) -> Unit,
    backgroundColor: Color = Surface1,
){
    val dialogShape = RoundedCornerShape(10.dp)

    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.background(backgroundColor).fillMaxWidth().wrapContentHeight()) {

        Column(
            modifier = Modifier.wrapContentSize(),
            horizontalAlignment = Alignment.Start) {
            Text(text = "Description", style = MaterialTheme.typography.h2, fontSize = 20.sp)

            Spacer(modifier = Modifier.height(5.dp))

            DescriptionTextField(
                description = description,
                onValueChange = onDescriptionChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(15.dp)
                    .clip(dialogShape)
                    .background(Color.White)
                    .border(width = 1.25.dp, color = Color.Black, shape = dialogShape))
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier
                .fillMaxWidth(0.5f)
                .wrapContentHeight()
                .padding(start = 5.dp, end = 5.dp)) {
                Text(
                    text = "Quantity",
                    style = MaterialTheme.typography.h2,
                    fontSize = 20.sp)

                NumberTextField(
                    value = quantity,
                    onValueChange = onQuantityChange,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .height(60.dp)
                        .clip(dialogShape)
                        .background(Color.White)
                        .border(width = 1.25.dp, color = Color.Black, shape = dialogShape))
            }

            Column(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(start = 5.dp, end = 5.dp)) {
                Text(
                    text = "Price",
                    style = MaterialTheme.typography.h2,
                    fontSize = 20.sp)

                NumberTextField(
                    value = price,
                    onValueChange = onPriceChange,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .height(60.dp)
                        .clip(dialogShape)
                        .background(Color.White)
                        .border(width = 1.25.dp, color = Color.Black, shape = dialogShape))
            }

        }


        Spacer(modifier = Modifier.height(10.dp))

        Column(modifier = Modifier
            .fillMaxWidth(0.5f)
            .wrapContentHeight()
            .padding(start = 10.dp, end = 10.dp),
             horizontalAlignment = Alignment.Start) {
            Text(
                text = "Total",
                style = MaterialTheme.typography.h2,
                fontSize = 20.sp)

            NumberTextField(
                value = subtotal,
                onValueChange = onSubtotalChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .clip(dialogShape)
                    .background(Color.White)
                    .border(width = 1.25.dp, color = Color.Black, shape = dialogShape))
        }



    }

}